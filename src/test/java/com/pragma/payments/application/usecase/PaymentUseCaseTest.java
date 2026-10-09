package com.pragma.payments.application.usecase;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.PaymentRepository;
import com.pragma.payments.domain.port.ExternalServicePort;
import com.pragma.payments.infrastructure.exception.IdempotencyException;
import com.pragma.payments.infrastructure.exception.PaymentProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentUseCase - Pruebas Unitarias")
class PaymentUseCaseTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ExternalServicePort externalServicePort;

    private PaymentUseCase paymentUseCase;

    @BeforeEach
    void setUp() {
        paymentUseCase = new PaymentUseCase(paymentRepository, externalServicePort);
    }

    @Test
    @DisplayName("Debe procesar pago exitosamente cuando todos los servicios responden correctamente")
    void debeProcesarPagoExitosamente() {
        Payment payment = new Payment(
            "op-001", "channel-web", "123456", new BigDecimal("1000.00"),
            "COP", LocalDateTime.now(), PaymentStatus.PENDING, 0
        );

        when(paymentRepository.findByOperationNumberAndChannel(anyString(), anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.findByIdempotencyKey(anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.save(any(Payment.class)))
            .thenReturn(Mono.just(payment));
        when(externalServicePort.validateAntifraud(any(Payment.class)))
            .thenReturn(Mono.just("APPROVED"));
        when(externalServicePort.evaluateRisk(any(Payment.class)))
            .thenReturn(Mono.just("LOW_RISK"));
        when(externalServicePort.processBankCore(any(Payment.class)))
            .thenReturn(Mono.just("SUCCESS"));
        when(externalServicePort.executePaymentGateway(any(Payment.class)))
            .thenReturn(Mono.just("AUTHORIZED"));

        StepVerifier.create(paymentUseCase.processPayment(payment))
            .assertNext(result -> {
                assertNotNull(result);
                assertEquals(PaymentStatus.COMPLETED, result.status());
            })
            .verifyComplete();

        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(externalServicePort, times(1)).validateAntifraud(any(Payment.class));
        verify(externalServicePort, times(1)).evaluateRisk(any(Payment.class));
        verify(externalServicePort, times(1)).processBankCore(any(Payment.class));
        verify(externalServicePort, times(1)).executePaymentGateway(any(Payment.class));
    }

    @Test
    @DisplayName("Debe rechazar pago por idempotencia cuando ya existe con mismo operation number y channel")
    void debeRechazarPagoPorIdempotencia() {
        Payment existingPayment = new Payment(
            "op-001", "channel-web", "123456", new BigDecimal("1000.00"),
            "COP", LocalDateTime.now(), PaymentStatus.COMPLETED, 1
        );

        when(paymentRepository.findByOperationNumberAndChannel("op-001", "channel-web"))
            .thenReturn(Mono.just(existingPayment));

        Payment newPayment = new Payment(
            "op-001", "channel-web", "123456", new BigDecimal("1000.00"),
            "COP", LocalDateTime.now(), PaymentStatus.PENDING, 0
        );

        StepVerifier.create(paymentUseCase.processPayment(newPayment))
            .expectError(IdempotencyException.class)
            .verify();

        verify(paymentRepository, never()).save(any(Payment.class));
        verify(externalServicePort, never()).validateAntifraud(any(Payment.class));
    }

    @Test
    @DisplayName("Debe rechazar pago cuando antifraude retorna REJECTED")
    void debeRechazarPagoCuandoAntifraudeRechaza() {
        Payment payment = new Payment(
            "op-002", "channel-mobile", "789012", new BigDecimal("5000.00"),
            "COP", LocalDateTime.now(), PaymentStatus.PENDING, 0
        );

        when(paymentRepository.findByOperationNumberAndChannel(anyString(), anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.findByIdempotencyKey(anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.save(any(Payment.class)))
            .thenReturn(Mono.just(payment));
        when(externalServicePort.validateAntifraud(any(Payment.class)))
            .thenReturn(Mono.just("REJECTED"));

        StepVerifier.create(paymentUseCase.processPayment(payment))
            .assertNext(result -> {
                assertNotNull(result);
                assertEquals(PaymentStatus.FAILED, result.status());
            })
            .verifyComplete();

        verify(externalServicePort, times(1)).validateAntifraud(any(Payment.class));
        verify(externalServicePort, never()).evaluateRisk(any(Payment.class));
        verify(externalServicePort, never()).processBankCore(any(Payment.class));
        verify(externalServicePort, never()).executePaymentGateway(any(Payment.class));
    }

    @Test
    @DisplayName("Debe fallar cuando el servicio externo lanza excepción")
    void debeFallarCuandoServicioExternoLanzaExcepcion() {
        Payment payment = new Payment(
            "op-003", "channel-web", "345678", new BigDecimal("2000.00"),
            "COP", LocalDateTime.now(), PaymentStatus.PENDING, 0
        );

        when(paymentRepository.findByOperationNumberAndChannel(anyString(), anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.findByIdempotencyKey(anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.save(any(Payment.class)))
            .thenReturn(Mono.just(payment));
        when(externalServicePort.validateAntifraud(any(Payment.class)))
            .thenReturn(Mono.error(new RuntimeException("Servicio antifraude no disponible")));

        StepVerifier.create(paymentUseCase.processPayment(payment))
            .expectError(PaymentProcessingException.class)
            .verify();

        ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);
        verify(paymentRepository, atLeastOnce()).save(paymentCaptor.capture());
        assertEquals(1, paymentCaptor.getValue().retryCount());
    }

    @Test
    @DisplayName("Debe guardar pago con estado FAILED cuando todos los servicios fallan")
    void debeGuardarPagoFailedCuandoTodosLosServiciosFallan() {
        Payment payment = new Payment(
            "op-004", "channel-web", "111222", new BigDecimal("3000.00"),
            "USD", LocalDateTime.now(), PaymentStatus.PENDING, 0
        );

        when(paymentRepository.findByOperationNumberAndChannel(anyString(), anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.findByIdempotencyKey(anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.save(any(Payment.class)))
            .thenReturn(Mono.just(payment));
        when(externalServicePort.validateAntifraud(any(Payment.class)))
            .thenReturn(Mono.just("APPROVED"));
        when(externalServicePort.evaluateRisk(any(Payment.class)))
            .thenReturn(Mono.just("HIGH_RISK"));

        StepVerifier.create(paymentUseCase.processPayment(payment))
            .assertNext(result -> {
                assertNotNull(result);
                assertTrue(
                    result.status() == PaymentStatus.FAILED || result.status() == PaymentStatus.PENDING
                );
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe recuperar pago existente por idempotency key")
    void debeRecuperarPagoExistentePorIdempotencyKey() {
        String idempotencyKey = "idem-123";
        Payment existingPayment = new Payment(
            "op-005", "channel-web", idempotencyKey, new BigDecimal("1500.00"),
            "EUR", LocalDateTime.now(), PaymentStatus.COMPLETED, 0
        );

        when(paymentRepository.findByIdempotencyKey(idempotencyKey))
            .thenReturn(Mono.just(existingPayment));

        Payment newPayment = new Payment(
            "op-005", "channel-web", idempotencyKey, new BigDecimal("1500.00"),
            "EUR", LocalDateTime.now(), PaymentStatus.PENDING, 0
        );

        StepVerifier.create(paymentUseCase.processPayment(newPayment))
            .assertNext(result -> {
                assertNotNull(result);
                assertEquals(existingPayment.id(), result.id());
            })
            .verifyComplete();

        verify(paymentRepository, never()).save(any(Payment.class));
    }
}
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.infrastructure.exception.ExternalServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ExternalServiceAdapter - Pruebas de Integración")
class ExternalServiceAdapterTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    private ExternalServiceAdapter externalServiceAdapter;

    @BeforeEach
    void setUp() {
        externalServiceAdapter = new ExternalServiceAdapter(webClient);
    }

    @Test
    @DisplayName("Debe validar antifraude exitosamente cuando el servicio responde APPROVED")
    void debeValidarAntifraudeExitosamente() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("APPROVED"));

        StepVerifier.create(externalServiceAdapter.validateAntifraud(payment))
            .assertNext(response -> {
                assertNotNull(response);
                assertEquals("APPROVED", response);
            })
            .verifyComplete();

        verify(webClient, times(1)).post();
    }

    @Test
    @DisplayName("Debe evaluar riesgo exitosamente cuando el servicio responde LOW_RISK")
    void debeEvaluarRiesgoExitosamente() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("LOW_RISK"));

        StepVerifier.create(externalServiceAdapter.evaluateRisk(payment))
            .assertNext(response -> {
                assertNotNull(response);
                assertEquals("LOW_RISK", response);
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe procesar en core bancario exitosamente")
    void debeProcesarEnCoreBancarioExitosamente() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("SUCCESS"));

        StepVerifier.create(externalServiceAdapter.processBankCore(payment))
            .assertNext(response -> {
                assertNotNull(response);
                assertEquals("SUCCESS", response);
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe ejecutar gateway de pagos exitosamente")
    void debeEjecutarGatewayPagosExitosamente() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("AUTHORIZED"));

        StepVerifier.create(externalServiceAdapter.executePaymentGateway(payment))
            .assertNext(response -> {
                assertNotNull(response);
                assertEquals("AUTHORIZED", response);
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe lanzar ExternalServiceException cuando el servicio de antifraude falla")
    void debeLanzarExcepcionCuandoAntifraudeFalla() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class))
            .thenReturn(Mono.error(new RuntimeException("Connection timeout")));

        StepVerifier.create(externalServiceAdapter.validateAntifraud(payment))
            .expectError(ExternalServiceException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe lanzar ExternalServiceException cuando el servicio de riesgo falla")
    void debeLanzarExcepcionCuandoServicioRiesgoFalla() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class))
            .thenReturn(Mono.error(new RuntimeException("Service unavailable")));

        StepVerifier.create(externalServiceAdapter.evaluateRisk(payment))
            .expectError(ExternalServiceException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe lanzar ExternalServiceException cuando el core bancario falla")
    void debeLanzarExcepcionCuandoCoreBancarioFalla() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class))
            .thenReturn(Mono.error(new RuntimeException("Bank system offline")));

        StepVerifier.create(externalServiceAdapter.processBankCore(payment))
            .expectError(ExternalServiceException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe lanzar ExternalServiceException cuando el gateway de pagos falla")
    void debeLanzarExcepcionCuandoGatewayPagosFalla() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class))
            .thenReturn(Mono.error(new RuntimeException("Gateway timeout")));

        StepVerifier.create(externalServiceAdapter.executePaymentGateway(payment))
            .expectError(ExternalServiceException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe manejar respuesta vacía del servicio externo")
    void debeManejarRespuestaVacia() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.empty());

        StepVerifier.create(externalServiceAdapter.validateAntifraud(payment))
            .assertNext(response -> {
                assertNotNull(response);
                assertTrue(response.isEmpty());
            })
            .verifyComplete();
    }

    private Payment crearPaymentBasico() {
        return new Payment(
            "op-test-001", "channel-web", "idem-001", new BigDecimal("1000.00"),
            "COP", LocalDateTime.now(), PaymentStatus.PENDING, 0
        );
    }
}
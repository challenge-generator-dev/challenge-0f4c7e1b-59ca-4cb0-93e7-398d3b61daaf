package com.pragma.payments.infrastructure.adapter;



import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.infrastructure.exception.PaymentProcessingException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.port.PaymentRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class PaymentMongoRepository implements PaymentRepository {

    private final ReactiveMongoTemplate mongoTemplate;
    private static final String COLLECTION_NAME = "payments";

    public PaymentMongoRepository(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Payment> save(Payment payment) {
        return mongoTemplate.save(payment, COLLECTION_NAME)
                .onErrorResume(e -> Mono.error(
                        new com.pragma.payments.infrastructure.exception.PaymentProcessingException(
                                "Error al guardar el pago en MongoDB: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Payment> findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Payment.class, COLLECTION_NAME)
                .switchIfEmpty(Mono.empty())
                .onErrorResume(e -> Mono.error(
                        new com.pragma.payments.infrastructure.exception.PaymentProcessingException(
                                "Error al buscar el pago por ID: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Payment> findByOperationNumberAndChannel(String operationNumber, String channel) {
        Query query = new Query()
                .addCriteria(Criteria.where("operationNumber").is(operationNumber))
                .addCriteria(Criteria.where("channel").is(channel));
        return mongoTemplate.findOne(query, Payment.class, COLLECTION_NAME)
                .switchIfEmpty(Mono.empty())
                .onErrorResume(e -> Mono.error(
                        new com.pragma.payments.infrastructure.exception.PaymentProcessingException(
                                "Error al buscar el pago por número de operación y canal: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Payment> findByIdempotencyKey(String idempotencyKey) {
        Query query = new Query(Criteria.where("idempotencyKey").is(idempotencyKey));
        return mongoTemplate.findOne(query, Payment.class, COLLECTION_NAME)
                .switchIfEmpty(Mono.empty())
                .onErrorResume(e -> Mono.error(
                        new com.pragma.payments.infrastructure.exception.PaymentProcessingException(
                                "Error al buscar el pago por clave de idempotencia: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.remove(query, COLLECTION_NAME)
                .then()
                .onErrorResume(e -> Mono.error(
                        new com.pragma.payments.infrastructure.exception.PaymentProcessingException(
                                "Error al eliminar el pago: " + e.getMessage(), e)));
    }

    public Mono<Payment> updateStatus(String id, Payment.PaymentStatus newStatus) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("status", newStatus.name());
        return mongoTemplate.findAndModify(query, update, Payment.class, COLLECTION_NAME)
                .switchIfEmpty(Mono.empty())
                .onErrorResume(e -> Mono.error(
                        new com.pragma.payments.infrastructure.exception.PaymentProcessingException(
                                "Error al actualizar el estado del pago: " + e.getMessage(), e)));
    }

    public Mono<Payment> findByOperationNumber(String operationNumber) {
        Query query = new Query(Criteria.where("operationNumber").is(operationNumber));
        return mongoTemplate.findOne(query, Payment.class, COLLECTION_NAME)
                .switchIfEmpty(Mono.empty())
                .onErrorResume(e -> Mono.error(
                        new com.pragma.payments.infrastructure.exception.PaymentProcessingException(
                                "Error al buscar el pago por número de operación: " + e.getMessage(), e)));
    }
}
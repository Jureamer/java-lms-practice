package nextstep.payments.domain;

public interface PaymentRepository {
    String findPaymentById(int paymentId);
    String findPaymentBySessionId(int sessionId);
    void save(Payment payment);
    String findLastPaymentId();
}

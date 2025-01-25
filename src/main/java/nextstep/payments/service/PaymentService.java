package nextstep.payments.service;

import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentRepository;
import nextstep.users.domain.NsUser;

public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;

    }
    public Payment payment(String id) {
        return new Payment();
    }

    public void pay(Long sessionId, NsUser user, Long charge) {
        String lastPaymentId = paymentRepository.findLastPaymentId();
        Payment payment = new Payment(lastPaymentId + 1, sessionId, user.getUserId(), charge);
        paymentRepository.save(payment);
    }
}

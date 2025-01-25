package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.infrastructure.SessionRepository;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;

public class SessionService {
    private SessionRepository sessionRepository;
    private PaymentService paymentService;

    public SessionService(SessionRepository sessionRepository, PaymentService paymentService) {
        this.sessionRepository = sessionRepository;
        this.paymentService = paymentService;
    }
    public void enroll(NsUser user, Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        session.validateEnrollment(user);

        if (!session.isFree()) {
            paymentService.pay(sessionId, user, session.getCharge());
        }

        session.enrollUser(user);
    }

}

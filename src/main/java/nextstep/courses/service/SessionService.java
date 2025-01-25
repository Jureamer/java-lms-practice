package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.infrastructure.SessionRepository;
import nextstep.users.domain.NsUser;

public class SessionService {
    private SessionRepository sessionRepository;

    public void enroll(NsUser user, Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        session.enroll(user);
    }

}

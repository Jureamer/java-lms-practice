package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;

public interface SessionRepository {
    Session findById(Long sessionId);
    int save(Session session);

}

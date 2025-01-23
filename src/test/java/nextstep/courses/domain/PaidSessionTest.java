package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaidSessionTest {
    CoverImage coverImage = new CoverImage(100, ImageType.JPEG, 300, 200);
    Status readyStatus = Status.READY;
    Session session = new Session("2021-08-01", "2021-08-08", coverImage, readyStatus);

    @Test
    void 유료_강의를_생성한다() {
        assertThat(new PaidSession(session, 10000, 20)).isNotNull();
    }

    @Test
    void 유료_강의는_수강료를_가진다() {
        PaidSession paidSession = new PaidSession(session, 10000, 20);
        assertThat(paidSession.getPrice()).isEqualTo(10000);
    }

    @Test
    void 유료_강의는_모집인원을_가진다() {
        PaidSession paidSession = new PaidSession(session, 10000, 20);
        assertThat(paidSession.getMaxEnrollment()).isEqualTo(20);
    }

    @Test
    void 현재_수강인원이_모집인원보다_적을_경우_수강_가능하다() {
        PaidSession paidSession = new PaidSession(session, 10000, 20);
        assertThat(paidSession.isEnrollable()).isTrue();
    }

    @Test
    void 현재_수강인원이_모집인원과_같을_경우_수강_불가능하다() {
        PaidSession paidSession = new PaidSession(session, 10000, 1);
        paidSession.enroll();

        assertThat(paidSession.isEnrollable()).isFalse();
    }
}

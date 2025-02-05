package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    private int DEFAULT_IMAGE_SIZE = 1024 * 1024;
    private CoverImage coverImage = new CoverImage(DEFAULT_IMAGE_SIZE, ImageType.JPEG, 300, 200);
    private Status readyStatus = Status.READY;
    private Enrollment enrollment = new Enrollment();
    private Long charge = 100L;
    private int capacity = 100;
    private RecruitState recruitState = RecruitState.OPEN;

    @Test
    void 강의는_시작일과_종료일을_가진다() {
        Session session = new Session("2021-08-01", "2021-08-08", coverImage, charge, enrollment);
        assertThat(session.getStartDateTime()).isEqualTo("2021-08-01");
        assertThat(session.getEndDateTime()).isEqualTo("2021-08-08");
    }

    @Test
    void 강의는_커버_이미지를_가진다() {
        Session session = new Session("2021-08-01", "2021-08-08", coverImage, charge, enrollment);
        assertThat(session.getCoverImage()).isEqualTo(coverImage);
    }

    @Test
    void 강의는_상태를_가진다() {
        Session session = new Session("2021-08-01", "2021-08-08", coverImage, charge, enrollment);
        assertThat(session.getStatus()).isEqualTo(readyStatus);
    }

    @Test
    void 무료_강의를_생성한다() {
        Session session = new Session("2021-08-01", "2021-08-08", coverImage, 0L, enrollment);
        assertThat(session.isFree()).isTrue();
    }

    @Test
    void 유료_강의를_생성한다() {
        Session session = new Session("2021-08-01", "2021-08-08", coverImage, charge, enrollment);
        assertThat(session.isFree()).isFalse();
    }

    @Test
    void 강의는_모집상태를_가진다() {
        Session session = new Session("2021-08-01", "2021-08-08", coverImage, charge, enrollment, recruitState);
        assertThat(session.getRecruitState()).isEqualTo(recruitState);
    }

    @Test
    void 준비_중_상태일때는_등록할_수_없다() {
        Session session = new Session("2021-08-01", "2021-08-08", coverImage, charge, enrollment);
        NsUser user = new NsUser();
        assertThatThrownBy(() -> session.validateEnrollment(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재는 강의에 등록할 수 없는 상태입니다.");
    }

    @Test
    void 이미_등록된_사용자는_등록할_수_없다() {
        Session session = new Session("2021-08-01", "2021-08-08", coverImage, charge, enrollment);
        NsUser user = new NsUser();
        session.enrollUser(user);
        assertThatThrownBy(() -> session.validateEnrollment(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 등록된 사용자입니다.");
    }

    @Test
    void 강의에_사용자를_등록한다() {
        Session session = new Session("2021-08-01", "2021-08-08", coverImage, charge, enrollment);
        NsUser user = new NsUser();
        session.enrollUser(user);
        assertThat(session.getRecruitState()).isEqualTo(RecruitState.OPEN);
        assertThat(session.isFree()).isFalse();
    }

    @Test
    void 비모집중_일때는_등록할_수_없다() {
        Session session = new Session("2021-08-01", "2021-08-08", coverImage, charge, enrollment, RecruitState.CLOSE);
        NsUser user = new NsUser();
        assertThatThrownBy(() -> session.validateEnrollment(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재는 강의에 등록할 수 없는 상태입니다.");
    }

    @Test
    void 종료된_강의는_등록할_수_없다() {
        Session session = new Session("2021-08-01", "2021-08-08", coverImage, charge, enrollment, RecruitState.CLOSE);
        NsUser user = new NsUser();
        assertThatThrownBy(() -> session.validateEnrollment(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재는 강의에 등록할 수 없는 상태입니다.");
    }
}

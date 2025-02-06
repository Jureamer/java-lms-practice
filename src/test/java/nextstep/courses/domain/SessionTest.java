package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    private int DEFAULT_IMAGE_SIZE = 1024 * 1024;
    private SessionDate sessionDate = new SessionDate.Builder()
            .startDateTime("2021-08-01")
            .endDateTime("2021-08-08")
            .build();
    private CoverImage coverImage = new CoverImage(DEFAULT_IMAGE_SIZE, ImageType.JPEG, 300, 200);
    private List<CoverImage> coverImages = List.of(coverImage);
    private Status readyStatus = Status.READY;
    private Enrollment enrollment = new Enrollment.Builder().build();
    private Long charge = 100L;
    private int capacity = 100;
    private RecruitState recruitState = RecruitState.OPEN;

    @Test
    void 강의는_시작일과_종료일을_가진다() {
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(charge)
                .enrollment(enrollment)
                .build();
        assertThat(session.getStartDateTime()).isEqualTo("2021-08-01");
        assertThat(session.getEndDateTime()).isEqualTo("2021-08-08");
    }

    @Test
    void 강의는_커버_이미지를_가진다() {
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(charge)
                .enrollment(enrollment)
                .build();
        assertThat(session.getCoverImages()).isEqualTo(coverImage);
    }

    @Test
    void 강의는_상태를_가진다() {
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(charge)
                .enrollment(enrollment)
                .build();
        assertThat(session.getStatus()).isEqualTo(readyStatus);
    }

    @Test
    void 무료_강의를_생성한다() {
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(0L)
                .enrollment(enrollment)
                .build();
        assertThat(session.isFree()).isTrue();
    }

    @Test
    void 유료_강의를_생성한다() {
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(charge)
                .enrollment(enrollment)
                .build();
        assertThat(session.isFree()).isFalse();
    }

    @Test
    void 강의는_모집상태를_가진다() {
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(charge)
                .enrollment(enrollment)
                .build();
        assertThat(session.getRecruitState()).isEqualTo(recruitState);
    }

    @Test
    void 준비_중_상태일때는_등록할_수_없다() {
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(charge)
                .enrollment(enrollment)
                .build();
        NsUser user = new NsUser();
        assertThatThrownBy(() -> session.validateEnrollment(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재는 강의에 등록할 수 없는 상태입니다.");
    }

    @Test
    void 이미_등록된_사용자는_등록할_수_없다() {
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(charge)
                .enrollment(enrollment)
                .build();
        NsUser user = new NsUser();
        session.enrollUser(user);
        assertThatThrownBy(() -> session.validateEnrollment(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 등록된 사용자입니다.");
    }

    @Test
    void 강의에_사용자를_등록한다() {
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(charge)
                .enrollment(enrollment)
                .build();
        NsUser user = new NsUser();
        session.enrollUser(user);
        assertThat(session.getRecruitState()).isEqualTo(RecruitState.OPEN);
        assertThat(session.isFree()).isFalse();
    }

    @Test
    void 비모집중_일때는_등록할_수_없다() {
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(charge)
                .enrollment(new Enrollment.Builder().recruitState(RecruitState.CLOSE).build())
                .build();
        NsUser user = new NsUser();
        assertThatThrownBy(() -> session.validateEnrollment(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재는 강의에 등록할 수 없는 상태입니다.");
    }

    @Test
    void 종료된_강의는_등록할_수_없다() {
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(charge)
                .enrollment(new Enrollment.Builder().recruitState(RecruitState.CLOSE).build())
                .build();
        NsUser user = new NsUser();
        assertThatThrownBy(() -> session.validateEnrollment(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재는 강의에 등록할 수 없는 상태입니다.");
    }

    @Test
    void 강의_정원을_초과하면_등록할_수_없다() {
        Enrollment enrollment = new Enrollment.Builder()
                .capacity(1)
                .build();
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(charge)
                .enrollment(enrollment)
                .build();
        NsUser user = new NsUser();
        for (int i = 0; i < capacity + 1; i++) {
            session.enrollUser(new NsUser());
        }
        assertThatThrownBy(() -> session.validateEnrollment(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재는 강의에 등록할 수 없는 상태입니다.");
    }
}

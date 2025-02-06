package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.Teacher;
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
    private Teacher teacher = new Teacher(new NsUser(10L, "강사", "강사@nextstep.camp", "강사", "강사입니다."));

    @Test
    void 강의는_시작일과_종료일을_가진다() {
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(charge)
                .enrollment(enrollment)
                .teacher(teacher)
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
                .teacher(teacher)
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
                .teacher(teacher)
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
                .teacher(teacher)
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
                .teacher(teacher)
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
                .teacher(teacher)
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
                .teacher(teacher)
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
                .teacher(teacher)
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
                .teacher(teacher)
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
                .teacher(teacher)
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
                .teacher(teacher)
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
                .teacher(teacher)
                .build();
        NsUser user = new NsUser();

        for (int i = 0; i < capacity; i++) {
            session.enrollUser(new NsUser());
        }
        assertThatThrownBy(() -> session.validateEnrollment(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재는 강의에 등록할 수 없는 상태입니다.");
    }

    @Test
    void 강사는_특정_유저를_수강_취소할_수_있다() {
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(charge)
                .enrollment(enrollment)
                .teacher(teacher)
                .build();
        NsUser user = new NsUser();
        session.enrollUser(user);
        session.cancelEnrollment(user, teacher);
        assertThat(session.isEnrolled(user)).isFalse();
    }

    @Test
    void 강사가_아닌_유저는_수강_취소할_수_없다() {
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(charge)
                .enrollment(enrollment)
                .teacher(teacher)
                .build();
        NsUser user = new NsUser();
        session.enrollUser(user);
        Teacher anotherTeacher = new Teacher(new NsUser(20L, "다른 강사", "다른강사@nextstep.camp", "다른강사", "다른강사입니다."));
        assertThatThrownBy(() -> session.cancelEnrollment(user, anotherTeacher))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강사만 수강 취소가 가능합니다.");
    }

    @Test
    void 강사는_특정_유저의_수강을_승인_할_수_있다() {
        Session session = new Session.Builder()
                .sessionDate(sessionDate)
                .coverImages(coverImages)
                .charge(charge)
                .enrollment(new Enrollment.Builder().recruitState(RecruitState.CLOSE).build())
                .teacher(teacher)
                .build();
        NsUser user = new NsUser(1L, "user", "1234", "user", "user@can");
        session.enrollUser(user);
        session.approveEnrollment(user, teacher);
        assertThat(session.isEnrolled(user)).isTrue();
    }
}

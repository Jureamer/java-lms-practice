package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {
    private int DEFAULT_IMAGE_SIZE = 1024 * 1024;
    private CoverImage coverImage = new CoverImage(DEFAULT_IMAGE_SIZE, ImageType.JPEG, 300, 200);
    private Status readyStatus = Status.READY;
    private Enrollment enrollment = new Enrollment(100);
    private Long charge = 100L;
    private int capacity = 100;

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
}

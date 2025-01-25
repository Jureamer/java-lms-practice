package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {
    private CoverImage coverImage = new CoverImage(100, ImageType.JPEG, 100, 100);
    private Status readyStatus = Status.READY;
    private Enrollment enrollment = new Enrollment(100);
    private int charge = 100;
    private int capacity = 100;

    @Test
    void 강의는_시작일과_종료일을_가진다() {
        Session session = new Session("2021-08-01", "2021-08-08", coverImage, charge, capacity, enrollment);
        assertThat(session.getStartDateTime()).isEqualTo("2021-08-01");
        assertThat(session.getEndDateTime()).isEqualTo("2021-08-08");
    }

    @Test
    void 강의는_커버_이미지를_가진다() {
        CoverImage coverImage = new CoverImage(100, ImageType.JPEG, 100, 100);
        Session session = new Session("2021-08-01", "2021-08-08", coverImage, charge, capacity, enrollment);
        assertThat(session.getCoverImage()).isEqualTo(coverImage);
    }

    @Test
    void 강의는_상태를_가진다() {
        Session session = new Session("2021-08-01", "2021-08-08", coverImage, charge, capacity, enrollment);
        assertThat(session.getStatus()).isEqualTo(readyStatus);
    }
}

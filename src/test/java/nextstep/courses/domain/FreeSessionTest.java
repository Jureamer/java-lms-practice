package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FreeSessionTest {
    CoverImage coverImage = new CoverImage(100, ImageType.JPEG, 300, 200);
    Status readyStatus = Status.READY;
    Session session = new Session("2021-08-01", "2021-08-08", coverImage, readyStatus);

    @Test
    void 무료_강의를_생성한다() {
        assertThat(new FreeSession(session)).isNotNull();
    }
}

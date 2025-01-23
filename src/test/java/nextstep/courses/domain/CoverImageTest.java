package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoverImageTest {

    @Test
    void 커버_이미지를_생성한다() {
        assertThat(new CoverImage(100, ImageType.JPEG, 100, 100)).isNotNull();
    }

    @Test
    void 이미지_1MB_이하여야_한다() {
        assertThat(new CoverImage(1024 * 1024 - 1, ImageType.JPEG, 100, 100)).isNotNull();
    }

    @Test
    void 이미지_1MB_이상이면_예외가_발생한다() {
        assertThatThrownBy(() -> new CoverImage(1024 * 1024, ImageType.JPEG, 100, 100))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

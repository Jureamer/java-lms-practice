package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoverImageTest {

    private int DEFAULT_WIDTH = 300;
    private int DEFAULT_HEIGHT = 200;
    @Test
    void 커버_이미지를_생성한다() {
        assertThat(new CoverImage(100, ImageType.JPEG, DEFAULT_WIDTH, DEFAULT_HEIGHT)).isNotNull();
    }

    @Test
    void 이미지_1MB_이하여야_한다() {
        assertThat(new CoverImage(1024 * 1024 - 1, ImageType.JPEG, DEFAULT_WIDTH, DEFAULT_HEIGHT)).isNotNull();
    }

    @Test
    void 이미지_1MB_초과면_예외가_발생한다() {
        assertThatThrownBy(() -> new CoverImage(1024 * 1024 + 1, ImageType.JPEG, DEFAULT_WIDTH, DEFAULT_HEIGHT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 크기는 1MB 이하이어야 합니다.");
    }
}

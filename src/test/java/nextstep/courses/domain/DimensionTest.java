package nextstep.courses.domain;

import nextstep.qna.domain.Dimension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DimensionTest {
    @Test
    void 이미지_크기를_생성한다() {
        assertThat(new Dimension(300, 200)).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {300, 5000})
    void 가로는_300_픽셀_이상이어야_한다() {
        assertThat(new Dimension(300, 200)).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 299})
    void 가로는_300_픽셀_미만이면_예외가_발생한다(int width) {
        assertThatThrownBy(() -> new Dimension(width, 100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가로는 300 픽셀 이상이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {300, 5000})
    void 세로는_200_픽셀_이상이어야_한다() {
        assertThat(new Dimension(300, 200)).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 199})
    void 세로는_200_픽셀_미만이면_예외가_발생한다(int height) {
        assertThatThrownBy(() -> new Dimension(300, 100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("세로는 200 픽셀 이상이어야 합니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"300, 200", "600, 400", "900, 600"})
    void 가로_세로_비율은_3_2이어야_한다(int width, int height) {
        assertThat(new Dimension(width, height)).isNotNull();
    }

    @ParameterizedTest
    @CsvSource(value = {"300, 201", "600, 401", "900, 601"})
    void 가로_세로_비율이_3_2가_아니면_예외가_발생한다(int width, int height) {
        assertThatThrownBy(() -> new Dimension(width, height))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가로 세로 비율은 3:2이어야 합니다.");
    }
}

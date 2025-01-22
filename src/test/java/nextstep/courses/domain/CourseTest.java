package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {
    @Test
    void 과정은_기수를_가진다() {
        Course course = new Course("자바지기", 1L, 1);
        assertThat(course.getCohort()).isEqualTo(1);
    }
}

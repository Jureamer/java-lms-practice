package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionDateTest {
    @Test
    void 강의_날짜를_생성한다() {
        String startDateTime = "2021-08-01";
        String endDateTime = "2021-08-08";
        SessionDate sessionDate = new SessionDate(startDateTime, endDateTime);

        assertThat(sessionDate.getStartDateTime()).isEqualTo(startDateTime);
        assertThat(sessionDate.getEndDateTime()).isEqualTo(endDateTime);
    }

    @Test
    void 시작날짜는_종료날짜보다_이전이어야_한다() {
        String startDateTime = "2021-08-01";
        String endDateTime = "2021-08-08";

        assertThatThrownBy(() -> new SessionDate(endDateTime, startDateTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작일이 종료일보다 늦을 수 없습니다.");
    }

    @Test
    void 시작날짜와_종료날짜는_같은_날짜일_수_없다() {
        String startDateTime = "2021-08-01";
        String endDateTime = "2021-08-01";

        assertThatThrownBy(() -> new SessionDate(startDateTime, endDateTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작일과 종료일은 같은 날짜일 수 없습니다.");
    }
}

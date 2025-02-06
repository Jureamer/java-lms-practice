package nextstep.courses.domain;

public class SessionDate {
    private String startDateTime;
    private String endDateTime;

    private SessionDate(String startDateTime, String endDateTime) {
        validateDateTime(startDateTime, endDateTime);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    private void validateDateTime(String startDateTime, String endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            throw new IllegalArgumentException("시작일과 종료일은 필수 값입니다.");
        }

        if (startDateTime.equals(endDateTime)) {
            throw new IllegalArgumentException("시작일과 종료일은 같은 날짜일 수 없습니다.");
        }

        if (startDateTime.compareTo(endDateTime) > 0) {
            throw new IllegalArgumentException("시작일이 종료일보다 늦을 수 없습니다.");
        }
    }

    public static class Builder {
        private String startDateTime;
        private String endDateTime;

        public Builder startDateTime(String startDateTime) {
            this.startDateTime = startDateTime;
            return this;
        }

        public Builder endDateTime(String endDateTime) {
            this.endDateTime = endDateTime;
            return this;
        }

        public SessionDate build() {
            return new SessionDate(startDateTime, endDateTime);
        }
    }
}

package nextstep.courses.domain;

public class Session {
    private SessionDate sessionDate;
    private CoverImage coverImage;
    private Status status;

    public Session(String startDateTime, String endDateTime, CoverImage coverImage, Status status) {
        this.sessionDate = new SessionDate(startDateTime, endDateTime);
        this.coverImage = coverImage;
        this.status = status;

    }

    public String getStartDateTime() {
        return sessionDate.getStartDateTime();
    }

    public String getEndDateTime() {
        return sessionDate.getEndDateTime();
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public Status getStatus() {
        return status;
    }
}

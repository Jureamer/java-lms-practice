package nextstep.courses.domain;

public class Session {
    private String startDateTime;
    private String endDateTime;
    private CoverImage coverImage;
    private Status status;

    public Session(String startDateTime, String endDateTime, CoverImage coverImage, Status status) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.coverImage = coverImage;
        this.status = status;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public Status getStatus() {
        return status;
    }
}

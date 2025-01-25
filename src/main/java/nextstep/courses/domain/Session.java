package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Session {
    private SessionDate sessionDate;
    private CoverImage coverImage;
    private Enrollment enrollment;
    private int charge;

    public Session(String startDateTime, String endDateTime, CoverImage coverImage, int charge, int capacity, Enrollment enrollment) {
        this.sessionDate = new SessionDate(startDateTime, endDateTime);
        this.coverImage = coverImage;
        this.charge = charge;
        this.enrollment = enrollment;
        
    }

    public boolean isEnrolled(NsUser user) {
        return enrollment.isEnrolled(user);
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

    public void enroll(NsUser user) {
    }

    public Status getStatus() {
        return enrollment.getStatus();
    }
}

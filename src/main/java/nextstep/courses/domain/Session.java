package nextstep.courses.domain;

import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;

public class Session {
    private SessionDate sessionDate;
    private CoverImage coverImage;
    private Enrollment enrollment;
    private Long charge;

    public Session(String startDateTime, String endDateTime, CoverImage coverImage, Long charge, Enrollment enrollment) {
        this.sessionDate = new SessionDate(startDateTime, endDateTime);
        this.coverImage = coverImage;
        this.charge = charge;
        this.enrollment = enrollment;
        
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

    public void enrollUser(NsUser user) {
        enrollment.enroll(user);
    }

    public Status getStatus() {
        return enrollment.getStatus();
    }

    public boolean isFree() {
        return charge == 0;
    }

    public void validateEnrollment(NsUser user) {
        if (enrollment.isEnrolled(user)) {
            throw new IllegalArgumentException("이미 등록된 사용자입니다.");
        }

        if (!enrollment.isOpen()) {
            throw new IllegalArgumentException("등록할 수 없습니다.");
        }
    }

    public Long getCharge() {
        return charge;
    }
}

package nextstep.courses.domain;

import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private SessionDate sessionDate;
    private List<CoverImage> coverImages;
    private Enrollment enrollment;
    private Long charge;

    private Session(SessionDate sessionDate, List<CoverImage> coverImages, Long charge, Enrollment enrollment) {
        this.sessionDate = sessionDate;
        this.coverImages = coverImages;
        this.charge = charge;
        this.enrollment = enrollment;
    }

    public String getStartDateTime() {
        return sessionDate.getStartDateTime();
    }

    public String getEndDateTime() {
        return sessionDate.getEndDateTime();
    }

    public List<CoverImage> getCoverImages() {
        return coverImages;
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

        if (!enrollment.canEnroll()) {
            throw new IllegalArgumentException("현재는 강의에 등록할 수 없는 상태입니다.");
        }
    }

    public Long getCharge() {
        return charge;
    }

    public RecruitState getRecruitState() {
        return enrollment.getRecruitState();
    }

    public static class Builder {
        private SessionDate sessionDate;
        private List<CoverImage> coverImages = new ArrayList<>();
        private Enrollment enrollment;
        private Long charge;

        public Builder sessionDate(SessionDate sessionDate) {
            this.sessionDate = sessionDate;
            return this;
        }

        public Builder coverImages(List<CoverImage> coverImages) {
            this.coverImages = coverImages;
            return this;
        }

        public Builder enrollment(Enrollment enrollment) {
            this.enrollment = enrollment;
            return this;
        }

        public Builder charge(Long charge) {
            this.charge = charge;
            return this;
        }

        public Session build() {
            return new Session(sessionDate, coverImages, charge, enrollment);
        }
    }
}

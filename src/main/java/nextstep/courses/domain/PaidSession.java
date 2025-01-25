package nextstep.courses.domain;

public class PaidSession {
    private Session session;
    private int fee;
    private int maxEnrollment;
    private int currentEnrollment = 0;

    public PaidSession(Session session, int fee, int maxEnrollment) {
        this.session = session;
        this.fee = fee;
        this.maxEnrollment = maxEnrollment;
    }

    public int getPrice() {
        return fee;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public boolean isEnrollable() {
        return currentEnrollment < maxEnrollment;
    }

    public void enroll() {
        if (!isEnrollable()) {
            throw new IllegalArgumentException("수강 인원이 초과되었습니다.");
        }
        currentEnrollment++;
    }
}

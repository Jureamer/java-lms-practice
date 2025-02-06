package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Enrollment {
    private static int MAX_CAPACITY = 9999;

    private int capacity;
    private List<NsUser> waitingUsers = new ArrayList<>();
    private List<NsUser> students = new ArrayList<>();
    private Status status;
    private RecruitState recruitState;

    private Enrollment(int capacity, Status status, RecruitState recruitState) {
        this.capacity = capacity;
        this.status = status;
        this.recruitState = recruitState;
    }

    public boolean canEnroll() {
        return isOpen() && recruitState == RecruitState.OPEN && capacity > students.size();
    }

    public Status getStatus() {
        return status;
    }

    public void enroll(NsUser user) {
        waitingUsers.add(user);
    }

    public boolean isEnrolled(NsUser user) {
        return students.contains(user);
    }

    public RecruitState getRecruitState() {
        return recruitState;
    }

    private boolean isOpen() {
        return status == Status.OPEN;
    }

    public void cancel(NsUser user) {
        waitingUsers.remove(user);
    }

    public void approve(NsUser user) {
        students.add(user);
        waitingUsers.remove(user);
    }

    public static class Builder {
        private int capacity = MAX_CAPACITY;
        private Status status = Status.READY;
        private RecruitState recruitState = RecruitState.OPEN;

        public Builder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Builder recruitState(RecruitState recruitState) {
            this.recruitState = recruitState;
            return this;
        }

        public Enrollment build() {
            return new Enrollment(capacity, status, recruitState);
        }
    }

}

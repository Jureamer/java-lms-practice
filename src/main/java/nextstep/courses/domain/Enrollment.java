package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Enrollment {
    private static int MAX_CAPACITY = 9999;

    private int capacity;
    private List<NsUser> users;
    private Status status;
    private RecruitState recruitState;

    private Enrollment(int capacity, List<NsUser> users, Status status, RecruitState recruitState) {
        this.capacity = capacity;
        this.users = users;
        this.status = status;
        this.recruitState = recruitState;
    }

    public boolean canEnroll() {
        return isOpen() && recruitState == RecruitState.OPEN && capacity > users.size();
    }

    public Status getStatus() {
        return status;
    }

    public void enroll(NsUser user) {
        users.add(user);
    }

    public boolean isEnrolled(NsUser user) {
        return users.contains(user);
    }

    public RecruitState getRecruitState() {
        return recruitState;
    }

    private boolean isOpen() {
        return status == Status.OPEN;
    }

    public static class Builder {
        private int capacity = MAX_CAPACITY;
        private List<NsUser> users = new ArrayList<>();
        private Status status = Status.READY;
        private RecruitState recruitState = RecruitState.OPEN;

        public Builder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder users(List<NsUser> users) {
            this.users = users;
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
            return new Enrollment(capacity, users, status, recruitState);
        }
    }

}

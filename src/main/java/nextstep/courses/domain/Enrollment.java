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

    public Enrollment() {
        this(MAX_CAPACITY, new ArrayList<>(), Status.READY, RecruitState.OPEN);
    }

    public Enrollment(int capacity, List<NsUser> users, Status status, RecruitState recruitState) {
        this.capacity = capacity;
        this.users = new ArrayList<>();
        this.status = status;
        this.recruitState = recruitState;
    }

    public Enrollment(int capacity, Status status) {
        this(capacity, new ArrayList<>(), status);
    }

    public Enrollment(int capacity, List<NsUser> users) {
        this(capacity, users, Status.READY);
    }

    public Enrollment(int capacity, List<NsUser> users, Status status) {
        this.capacity = capacity;
        this.users = users;
        this.status = status;
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
}

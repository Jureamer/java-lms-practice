package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Enrollment {
    private static int MAX_CAPACITY = 9999;

    private int capacity;
    private List<NsUser> users;
    private Status status;

    public Enrollment() {
        this(MAX_CAPACITY);
    }

    public Enrollment(int capacity) {
        this.capacity = capacity;
        this.users = new ArrayList<>();
        this.status = Status.READY;
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

    public boolean isEnrolled(NsUser user) {
        return users.contains(user);
    }

    public boolean isOpen() {
        return status == Status.OPEN;
    }

    public Status getStatus() {
        return status;
    }
}

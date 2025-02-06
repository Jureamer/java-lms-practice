package nextstep.users.domain;

public class Teacher {
    private NsUser user;

    public Teacher(NsUser user) {
        this.user = user;
    }


    public boolean match(Teacher teacher) {
        return this.user.matchUser(teacher);
    }

    public String getUserId() {
        return user.getUserId();
    }
}

package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Answers {
    List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<DeleteHistory> deleteAll() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers.stream()
                .forEach(answer -> {
                    answer.delete();
                    deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
                });
        return deleteHistories;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void checkAnswersOwner(NsUser loginUser) {

    }
}

package nextstep.courses.domain;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class FreeSession {
    private Session session;

    public FreeSession(Session session) {
        this.session = session;
    }


}

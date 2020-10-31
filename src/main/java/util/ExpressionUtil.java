package util;

import model.Expression;

import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

public class ExpressionUtil {
    private final Random random;

    private ExpressionUtil(Random random) {
        this.random = random;
    }

    public static Expression generateExpression() {
        Random random = new Random();

        ExpressionUtil expressionUtil = new ExpressionUtil(random);

        OptionalInt optionalInt = random.ints(0, Expression.Operation.values().length)
                .findFirst();
        Expression.Operation operation = optionalInt.isPresent() ?
                Expression.Operation.values()[optionalInt.getAsInt()] : Expression.Operation.ADDITION;

        OptionalInt firstOptional = expressionUtil.getRandomFieldIntStream().findAny();
        OptionalInt secondOptional = expressionUtil.getRandomFieldIntStream().findAny();
        int a = firstOptional.isPresent() ? firstOptional.getAsInt() : 0;
        int b = secondOptional.isPresent() ? secondOptional.getAsInt() : 0;

        return new Expression(a, b, operation);
    }

    private IntStream getRandomFieldIntStream() {
        return random.ints(-100, 100);
    }
}

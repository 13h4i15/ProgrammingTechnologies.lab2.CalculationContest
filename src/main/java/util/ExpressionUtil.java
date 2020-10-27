package util;

import model.Expression;

import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

public class ExpressionUtil {
    public static Expression generateExpression() {
        Random random = new Random();
        OptionalInt optionalInt = random.ints(0, Expression.Operation.values().length)
                .findFirst();
        Expression.Operation operation = optionalInt.isPresent() ?
                Expression.Operation.values()[optionalInt.getAsInt()] : Expression.Operation.ADDITION;

        IntStream intStream = random.ints();
        OptionalInt firstOptional = intStream.findAny();
        OptionalInt secondOptional = intStream.findAny();
        int a = firstOptional.isPresent() ? firstOptional.getAsInt() : 0;
        int b = secondOptional.isPresent() ? secondOptional.getAsInt() : 0;

        return new Expression(a, b, operation);
    }
}

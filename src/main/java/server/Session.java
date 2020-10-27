package server;

import com.google.gson.Gson;
import model.Answer;
import model.Expression;
import model.Result;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

class Session extends Thread implements Closeable {
    @NotNull
    private Gson gson = new Gson();
    @NotNull
    private final Socket firstPlayer, secondPlayer;

    public Session(@NotNull Socket firstPlayer, @NotNull Socket secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        start();
    }

    @Override
    public void run() {
        super.run();
        try (
                BufferedReader firstPlayerReader = new BufferedReader(new InputStreamReader(firstPlayer.getInputStream()));
                BufferedReader secondPlayerReader = new BufferedReader(new InputStreamReader(secondPlayer.getInputStream()));
                PrintWriter firstPlayerWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(firstPlayer.getOutputStream())), true);
                PrintWriter secondPlayerWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(secondPlayer.getOutputStream())), true)
        ) {
            Expression expression = generateExpression();

            sendAll(firstPlayerWriter, secondPlayerWriter, gson.toJson(expression));

            Answer answer = null;
            PrintWriter answeredPlayer = null, notAnsweredPlayer = null;
            while (answer == null) {
                if (firstPlayerReader.ready()) {
                    answer = gson.fromJson(firstPlayerReader, Answer.class);
                    answeredPlayer = firstPlayerWriter;
                    notAnsweredPlayer = secondPlayerWriter;
                } else if (secondPlayerReader.ready()) {
                    answer = gson.fromJson(secondPlayerReader, Answer.class);
                    answeredPlayer = secondPlayerWriter;
                    notAnsweredPlayer = firstPlayerWriter;
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignore) {
                    }
                }
            }

            boolean isCorrectAnswer = answer.getValue() == expression.getResult();

            Result winnerResult = new Result(expression.getResult(), Result.Title.WIN);
            Result looserResult = new Result(expression.getResult(), Result.Title.LOOSE);
            gson.toJson(isCorrectAnswer ? winnerResult : looserResult, answeredPlayer);
            gson.toJson(isCorrectAnswer ? looserResult : winnerResult, notAnsweredPlayer);
            close();
        } catch (IOException ioException) {

        }
    }

    @Override
    public void close() throws IOException {
        if (!firstPlayer.isClosed()) firstPlayer.close();
        if (!secondPlayer.isClosed()) secondPlayer.close();
    }

    private static void sendAll(@NotNull PrintWriter firstWriter, @NotNull PrintWriter secondWriter, String message) {
        firstWriter.println(message);
        secondWriter.println(message);
    }

    private static Expression generateExpression() {
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

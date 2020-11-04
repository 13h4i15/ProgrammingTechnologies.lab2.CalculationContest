package server;

import com.google.gson.Gson;
import model.Answer;
import model.Expression;
import model.Result;
import org.jetbrains.annotations.NotNull;
import util.ExpressionUtil;

import java.io.*;
import java.net.Socket;

class Session extends Thread {
    @NotNull
    private final Gson gson = new Gson();
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
                firstPlayer; secondPlayer;
                BufferedReader firstPlayerReader = new BufferedReader(new InputStreamReader(firstPlayer.getInputStream()));
                BufferedReader secondPlayerReader = new BufferedReader(new InputStreamReader(secondPlayer.getInputStream()));
                PrintWriter firstPlayerWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(firstPlayer.getOutputStream())), true);
                PrintWriter secondPlayerWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(secondPlayer.getOutputStream())), true)
        ) {
            Expression expression = ExpressionUtil.generateExpression();

            sendAll(firstPlayerWriter, secondPlayerWriter, gson.toJson(expression));

            Answer answer = null;
            PrintWriter answeredPlayer = null, notAnsweredPlayer = null;
            Boolean isSecondReady = null;
            while (answer == null || !isSecondReady) {
                if (firstPlayerReader.ready()) {
                    if (isSecondReady == null) {
                        answer = gson.fromJson(firstPlayerReader.readLine(), Answer.class);
                        answeredPlayer = firstPlayerWriter;
                        notAnsweredPlayer = secondPlayerWriter;
                        isSecondReady = false;
                    } else if (!isSecondReady) {
                        isSecondReady = true;
                    }
                } else if (secondPlayerReader.ready()) {
                    if (isSecondReady == null) {
                        answer = gson.fromJson(secondPlayerReader.readLine(), Answer.class);
                        answeredPlayer = secondPlayerWriter;
                        notAnsweredPlayer = firstPlayerWriter;
                        isSecondReady = false;
                    } else if (!isSecondReady) {
                        isSecondReady = true;
                    }
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignore) {
                    }
                }
            }

            boolean isCorrectAnswer = answer.getValue() == expression.getResult();

            Result winnerResult = new Result(expression.getResult(), isCorrectAnswer ? Result.Title.WIN_RIGHT_ANSWER : Result.Title.WIN_OPPONENT_LOST);
            Result loserResult = new Result(expression.getResult(), isCorrectAnswer ? Result.Title.LOSE_SECOND : Result.Title.LOSE_WRONG_ANSWER);
            answeredPlayer.println(gson.toJson(isCorrectAnswer ? winnerResult : loserResult));
            notAnsweredPlayer.println(gson.toJson(isCorrectAnswer ? loserResult : winnerResult));
        } catch (IOException ignore) {

        }
    }

    private static void sendAll(@NotNull PrintWriter firstWriter, @NotNull PrintWriter secondWriter, String message) {
        firstWriter.println(message);
        secondWriter.println(message);
    }
}

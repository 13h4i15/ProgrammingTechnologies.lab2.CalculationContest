package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.module.jsonSchema.annotation.JsonHyperSchema;

@JsonHyperSchema
@JsonAutoDetect
public class SessionFinal {
    @JsonProperty("date")
    private final long timeInMillis;

    @JsonProperty("first_player_answer")
    private final double firstPlayerAnswer;

    @JsonProperty("second_player_answer")
    private final double secondPlayerAnswer;

    @JsonProperty("correct_answer")
    private final double correctAnswer;

    @JsonCreator
    public SessionFinal(@JsonProperty("date") long timeInMillis,
                        @JsonProperty("first_player_answer") double firstPlayerAnswer,
                        @JsonProperty("second_player_answer") double secondPlayerAnswer,
                        @JsonProperty("correct_answer")double correctAnswer) {
        this.timeInMillis = timeInMillis;
        this.firstPlayerAnswer = firstPlayerAnswer;
        this.secondPlayerAnswer = secondPlayerAnswer;
        this.correctAnswer = correctAnswer;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public double getFirstPlayerAnswer() {
        return firstPlayerAnswer;
    }

    public double getSecondPlayerAnswer() {
        return secondPlayerAnswer;
    }

    public double getCorrectAnswer() {
        return correctAnswer;
    }
}

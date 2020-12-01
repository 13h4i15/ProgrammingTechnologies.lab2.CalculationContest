package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.module.jsonSchema.annotation.JsonHyperSchema;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@JsonHyperSchema
@JsonAutoDetect
public class SessionFinal {
    @JsonProperty("date")
    @NotNull
    private final Date date;

    @JsonProperty("winner_answer")
    private final double winnerAnswer;

    @JsonProperty("correct_answer")
    private final double correctAnswer;

    @JsonCreator
    public SessionFinal(@JsonProperty("date") @NotNull Date date,
                        @JsonProperty("winner_answer") double winnerAnswer,
                        @JsonProperty("correct_answer") double correctAnswer) {
        this.date = date;
        this.winnerAnswer = winnerAnswer;
        this.correctAnswer = correctAnswer;
    }

    @NotNull
    public Date getDate() {
        return date;
    }

    public double getWinnerAnswer() {
        return winnerAnswer;
    }

    public double getCorrectAnswer() {
        return correctAnswer;
    }
}

package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@JsonAutoDetect
public class Result {
    @JsonProperty("value")
    private final double correctVale;

    @NotNull
    @JsonProperty("result")
    private final Title resultValue;

    @JsonCreator
    public Result(@JsonProperty("value") double correctVale,
                  @JsonProperty("result") @NotNull Title resultValue) {
        this.correctVale = correctVale;
        this.resultValue = resultValue;
    }

    @Override
    public String toString() {
        return "Result: " + resultValue + " Value: " + correctVale;
    }

    public enum Title {
        WIN_RIGHT_ANSWER {
            @Override
            public String toString() {
                return "Win! You were right!";
            }
        },
        WIN_OPPONENT_LOST {
            @Override
            public String toString() {
                return "Win! Your opponent has lost!";
            }
        },
        LOSE_WRONG_ANSWER {
            @Override
            public String toString() {
                return "Lose! You enter wrong value!";
            }
        },
        LOSE_SECOND {
            @Override
            public String toString() {
                return "Lose! You were second!";
            }
        }
    }
}

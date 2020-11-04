package model;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

public class Result {
    @SerializedName("value")
    private final double correctVale;

    @NotNull
    @SerializedName("result")
    private final Title resultValue;

    public Result(double correctVale, @NotNull Title resultValue) {
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

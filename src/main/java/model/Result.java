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

    public double getCorrectVale() {
        return correctVale;
    }

    @NotNull
    public Title getResultValue() {
        return resultValue;
    }

    public enum Title {
        WIN,
        LOOSE
    }
}

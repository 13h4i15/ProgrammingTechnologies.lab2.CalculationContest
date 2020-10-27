package model;


import com.google.gson.annotations.SerializedName;

public class Answer {
    @SerializedName("answer")
    private final double value;

    public Answer(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

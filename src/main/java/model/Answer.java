package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class Answer {
    @JsonProperty("answer")
    private final double value;

    @JsonCreator
    public Answer(@JsonProperty("answer") double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

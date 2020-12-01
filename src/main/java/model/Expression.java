package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@JsonAutoDetect
public class Expression {
    @JsonProperty("a")
    private final int a;

    @JsonProperty("b")
    private final int b;

    @NotNull
    @JsonProperty("operation")
    private final Operation operation;

    @JsonCreator
    public Expression(@JsonProperty("a") int a,
                      @JsonProperty("b") int b,
                      @JsonProperty("operation") @NotNull Operation operation) {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }

    @JsonIgnore
    public double getResult() {
        return switch (operation) {
            case ADDITION -> a + b;
            case DIFFERENCE -> a - b;
            case MULTIPLY -> a * b;
            case DIVISION -> (double) a / b;
        };
    }

    @Override
    public String toString() {
        return String.format("%d %s %d = ", a, operation, b);
    }

    public enum Operation {
        ADDITION {
            @Override
            public String toString() {
                return "+";
            }
        },
        DIFFERENCE {
            @Override
            public String toString() {
                return "-";
            }
        },
        MULTIPLY {
            @Override
            public String toString() {
                return "*";
            }
        },
        DIVISION {
            @Override
            public String toString() {
                return "/";
            }
        }
    }
}

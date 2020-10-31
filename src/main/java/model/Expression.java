package model;


import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

public class Expression {
    @SerializedName("a")
    private final int a;

    @SerializedName("b")
    private final int b;

    @NotNull
    @SerializedName("operation")
    private final Operation operation;

    public Expression(int a, int b, @NotNull Operation operation) {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }

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

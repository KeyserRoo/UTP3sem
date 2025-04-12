package zad4;

import java.util.function.Function;

public class InputConverter<T> {

    private final T input;

    public InputConverter(T input) {
        this.input = input;
    }

    public <R> R convertBy(Function... functions) {
        Object result = input;
        for (Function function : functions) {
            result = function.apply(result);
        }
        return (R) result;
    }
}

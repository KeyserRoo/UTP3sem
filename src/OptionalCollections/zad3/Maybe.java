package zad3;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    T val;

    public Maybe() {
    }

    public Maybe(T val) {
        this.val = val;
    }

    public static <R> Maybe<R> of(R val) {
        return new Maybe<>(val);
    }

    public void ifPresent(Consumer consumer) {
        if (this.val != null)
            consumer.accept(val);
    }

    public <R> Maybe<R> map(Function<T, R> function) {
        if (val == null)
            return new Maybe<>();
        if (function.apply(val) == null)
            return new Maybe<>();
        return new Maybe<>(function.apply(val));
    }

    public T get() {
        if (val == null)
            throw new NoSuchElementException("Maybe is empty");
        return this.val;
    }

    public T orElse(T t) {
        if (this.val == null)
            return t;
        else
            return val;
    }

    public Maybe<T> filter(Predicate<T> pred) {
        if (pred.test(this.val) || (this.val == null)) {
            return this;
        } else
            return new Maybe<>();
    }

    @Override
    public String toString() {
        return val == null ? "Maybe is empty" : ("Maybe has value " + val);
    }
}

package Chapter3_lambda;

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}

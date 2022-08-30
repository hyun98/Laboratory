package Chapter1_2;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
package Chapter3_lambda;

// 인수가 3개인 생성자의 생성자 참조를 사용하기 위한 커스텀 인터페이스
// 생성자 참조와 일치하는 시그니터를 갖는 함수형 인터페이스가 필요함
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, R r);
}

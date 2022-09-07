package Chapter5_Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PracticeCode {

    
    public static void main(String[] args) {
        iterateMethod();
        quiz5_4_fibo();
    }


    static void pythagoreanTriples() {
        IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(1, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0));
        
    }

    static void iterateMethod() {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    static void quiz5_4_fibo() {
        UnaryOperator<int[]> function = (int[] lst) -> new int[]{lst[1], lst[0] + lst[1]};
        Stream.iterate(new int[]{0, 1}, function)
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);

        IntSupplier fib = new IntSupplier() {
            private int prev = 0;
            private int curr = 1;

            @Override
            public int getAsInt() {
                int old = this.prev;
                int next = this.curr + this.prev;
                this.prev = this.curr;
                this.curr = next;
                return old;
            }
        };
        
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
    
}

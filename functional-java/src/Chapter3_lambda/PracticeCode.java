package Chapter3_lambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static Chapter3_lambda.ExecuteAroundPattern.processFile;

public class PracticeCode {
    public static void main(String[] args) throws IOException{
        lambdaTest();
        executeAroundPatternTest();
    }

    static void sameTypeFunctionTest() {
        Callable<Integer> integerCallable = () -> 42;
        PrivilegedAction<Integer> integerPrivilegedAction = () -> 42;

        Object hello = (Runnable) () -> {
            System.out.println("hello");
        };
    }

    static void executeAroundPatternTest() throws IOException {
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        String twoLine = processFile((BufferedReader br) -> br.readLine() + br.readLine());

        System.out.println("oneLine = " + oneLine);
        System.out.println("twoLine = " + twoLine);
    }

    static void functionTest() {
        Function<String, Integer> function = (String s) -> s.length();
        Consumer<String> stringConsumer = (String s) -> {};
        Supplier<String> stringCallable = () -> "hello";
        
        FunctionExample functionExample = new FunctionExample();
        List<Integer> map = functionExample.map(Arrays.asList("lambdas", "in", "action"), function);

        // 명시적 예외 처리
        Function<BufferedReader, String> f = (BufferedReader b) -> {
            try {
                return b.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    static void lambdaTest() {
        // 람다 사용
        Runnable r1 = () -> System.out.println("Hello world");

        // 익명 클래스 사용
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world 2");

            }
        };

        process(r1);
        process(r2);
        // 람다 표현식 바로 전달
        process(() -> System.out.println("Hello world 3"));
    }

    static void process(Runnable r) {
        r.run();
    }
}

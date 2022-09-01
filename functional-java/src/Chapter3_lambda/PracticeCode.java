package Chapter3_lambda;

import Chapter1_2.Apple;
import Chapter1_2.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.*;

import static Chapter1_2.Color.GREEN;
import static Chapter1_2.Color.RED;
import static Chapter3_lambda.ExecuteAroundPattern.processFile;

public class PracticeCode {
    
    public static void main(String[] args) throws IOException {
        lambdaTest();
        executeAroundPatternTest();
    }

    static void predicateCombinationTest() {
        Predicate<Apple> redApple = (apple -> apple.getColor().equals(RED));
        
        // predicate 반전시키기 : negate()
        Predicate<Apple> notRedApple = redApple.negate();
        
        // predicate 조건 추가하기
        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);
        Predicate<Apple> redAndHeavyAppleOrGreen = redAndHeavyApple.or(apple -> GREEN.equals(apple.getColor()));
        
        // 여기 적힌 and, or 등은 왼쪽에서 오른쪽으로 연결됨
        // a.or(b).and(c) 는 (a || b) && c 와 같다.
    }

    static void functionCombinationTest() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        
        // 언박싱
        int result = h.apply(1);
    }


    // string 과 integer 가 주어졌을 때 다양한 무게를 갖는 
    // 여러 종류의 과일을 만드는 giveMeFruit 메서드를 만들 수 있다.
    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();

    static {
        map.put("Apple", Apple::new);
        map.put("Orange", Orange::new);
    }

    static Fruit giveMeFruit(String fruit, Integer weight) {
        return map.get(fruit.toLowerCase()).apply(weight);
    }

    static void constructorRefTest() {
        Supplier<Apple> c1 = Apple::new;
        Apple apple = c1.get();
        BiFunction<Integer, Color, Apple> aNew = Apple::new;
    }

    static void lambdaCapturingTest() {
        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);
        // 람다에서 참고하는 지역 변수는 final로 선언되거나 실질적으로 final 처럼 취급되어야 한다.
//        portNumber = 31337;
    }

    static void methodRefTest() {
        ToIntFunction<String> stringIntegerFunction = Integer::parseInt;
        BiPredicate<List<String>, String> contains = List::contains;
    }

    static void sameTypeFunctionTest() {
        Callable<Integer> integerCallable = () -> 42;
        PrivilegedAction<Integer> integerPrivilegedAction = () -> 42;

        Object hello = (Runnable) () -> {
            System.out.println("hello");
        };
    }

    static void executeAroundPatternTest() throws IOException {
        String oneLine = processFile(BufferedReader::readLine);
        String twoLine = processFile((BufferedReader br) -> br.readLine() + br.readLine());

        System.out.println("oneLine = " + oneLine);
        System.out.println("twoLine = " + twoLine);
    }

    static void functionTest() {
        Function<String, Integer> function = (String s) -> s.length();
        Consumer<String> stringConsumer = (String s) -> {
        };
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

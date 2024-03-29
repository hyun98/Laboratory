package Chapter1_2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Chapter1_2.Apple.filterApples;
import static Chapter1_2.Apple.prettyPrintApple;
import static Chapter1_2.Color.RED;

public class PracticeCode {
    
    public static void main(String[] args) {
//        lambdaTest();
        streamTest();
//        futureBit();
    }

    static void lambdaTest() {
        List<Apple> inventory = new ArrayList<>();

        Apple apple = new Apple(160, RED);
        inventory.add(apple);

        filterApples(inventory, Apple::isHeavyApple);
        filterApples(inventory, (Apple a) -> a.getWeight() > 150);

        List<Apple> apples = filterApples(inventory, new AppleRedAndHeavyPredicate());

        prettyPrintApple(inventory, new AppleFancyFormatter());

        filterApples(inventory, apple1 -> RED.equals(apple1.getColor()));

        inventory.sort(Comparator.comparing(Apple::getWeight));

        
    }

    static void streamTest() {
        final List<String> strings = new ArrayList<>();
        strings.add("hi");
        strings.add("ho");
        strings.add("hello");

        final Stream<String> stream = strings.stream();
        strings.forEach(System.out::println);

        List<String> res = stream.map(s -> s + " new").collect(Collectors.toList());
        
        res.forEach(System.out::println);

        int num = 3;
        IntBinaryOperator sum = (int a, int b) -> a + b + num;
//        num = 4;

//        Map<Integer, List<String>> lengthByString = strings.stream()
//                .parallel()
//                .filter((String s) -> s.length() > 1)
//                .collect(Collectors.groupingBy(String::length));
//
//        lengthByString.forEach((a, b) -> {
//            System.out.println("len : " + a);
//            System.out.println("strings : " + b);
//        });
    }

    static void futureBit() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName();
            }
        });

        try {
            String s = submit.get();
            System.out.println("s = " + s);

        } catch (Exception e) {
            e.getMessage();
        }
    }
}

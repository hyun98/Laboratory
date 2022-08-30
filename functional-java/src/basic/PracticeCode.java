package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static basic.Apple.filterApples;
import static basic.Apple.prettyPrintApple;
import static basic.Color.RED;

public class PracticeCode {
    
    public static void main(String[] args) {
        lambdaTest();
        streamTest();
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
    }

    static void streamTest() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("hi");
        strings.add("ho");
        strings.add("hihi");
        
        Map<Integer, List<String>> lengthByString = strings.stream()
                .parallel()
                .filter((String s) -> s.length() > 1)
                .collect(Collectors.groupingBy(String::length));

        lengthByString.forEach((a, b) -> {
            System.out.println("len : " + a);
            System.out.println("strings : " + b);
        });
    }
}

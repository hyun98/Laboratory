package basic;

import java.util.ArrayList;
import java.util.List;

public class Apple {
    private int weight;

    public Apple(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                System.out.println("over");
                
                result.add(apple);
            }
        }
        return result;
    }
}

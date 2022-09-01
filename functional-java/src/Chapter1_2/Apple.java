package Chapter1_2;

import Chapter3_lambda.Fruit;

import java.util.ArrayList;
import java.util.List;

import static Chapter1_2.Color.GREEN;


public class Apple implements Fruit {
    
    private int weight;

    private Color color = GREEN;

    public Apple() {

    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }


    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color=" + color +
                '}';
    }
}

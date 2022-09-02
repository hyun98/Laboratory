package Chapter4Stream;

import java.util.*;
import java.util.stream.Collectors;

import static Chapter4Stream.Dish.Type.*;

public class PracticeCode {
    
    private static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, MEAT),
            new Dish("beef", false, 700, MEAT),
            new Dish("chicken", false, 400, MEAT),
            new Dish("french fries", true, 530, OTHER),
            new Dish("rice", true, 350, OTHER),
            new Dish("season fruit", true, 120, OTHER),
            new Dish("pizza", true, 550, OTHER),
            new Dish("prawns", false, 300, FISH),
            new Dish("salmon", false, 450, FISH)
    );
    
    public static void main(String[] args) {
        quiz4_1();
    }

    public static void quiz4_1() {
        List<String> highCaloricDishes = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Dish next = iterator.next();
            if (next.getCalories() > 300) {
                highCaloricDishes.add(next.getName());
            }
        }
        
        // . . .

        List<String> result = menu.stream()
                .filter(d -> {
                    System.out.println("filtering : " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("mapping : " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(result);

        Comparator<Integer> cmp = (Integer a, Integer b) -> a + b;
    }
}

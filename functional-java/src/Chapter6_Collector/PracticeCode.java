package Chapter6_Collector;

import Chapter4Stream.Dish;

import java.util.*;
import java.util.stream.Stream;

import static Chapter4Stream.Dish.Type.*;
import static java.util.stream.Collectors.*;

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
        Comparator<Dish> sort = Comparator.comparing(Dish::getCalories);

        // max 는 맨 뒤 값을 빼는듯
        Optional<Dish> collect = menu.stream().max(sort);

        System.out.println("collect = " + collect.get().getName());

        String shortMenu = menu.stream().map(Dish::getName).collect(joining(" "));
        System.out.println("shortMenu = " + shortMenu);

        Integer collect1 = menu.stream()
                .collect(reducing(0, Dish::getCalories, (a, b) -> {
                    System.out.println("a = " + a);
                    return a + b;
                }));
        
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        List<Integer> numbers = stream.reduce(
                new ArrayList<>(),
                (List<Integer> l, Integer e) -> {
                    l.add(e);
                    return l;
                },
                (List<Integer> l1, List<Integer> l2) -> {
                    System.out.println("l1.size() = " + l1.size());
                    System.out.println("l2.size() = " + l2.size());
                    l1.addAll(l2);
                    System.out.println("l1.size() = " + l1.size());
                    return l1;
                }
        );
        
        numbers.forEach(System.out::println);

        
        Map<Dish.Type, List<Dish>> res1 = menu.stream()
                .filter(d -> d.getCalories() > 500)
                .collect(groupingBy(Dish::getType));

        Map<Dish.Type, List<Dish>> res2 = menu.stream()
                .collect(groupingBy(Dish::getType, filtering(d -> d.getCalories() > 500, toList())));
        
        /*
            res1 과 res2 의 차이는?
            
            res1은 FISH type인 경우가 filter에서 모두 걸러져서 결과 Map의 Key에 FISH가 아예 없다.
            res2는 그룹화 이후 predicate를 적용하기 때문에 FISH Key는 빈 리스트를 value로 갖게 된다.
         */


        menu.stream()
                .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));

    }
}

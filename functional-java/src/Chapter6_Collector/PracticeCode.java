package Chapter6_Collector;

import Chapter4Stream.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Chapter4Stream.Dish.Type.*;
import static Chapter6_Collector.CaloricLevel.*;
import static java.util.Comparator.comparingInt;
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
//        groupingPractice();
//        multiLevelGrouping();
        partitionPractice();
        
        // 커스텀 컬렉터 구현 직전까지 공부.
    }

    static void collectorBasic() {
        Comparator<Dish> sort = Comparator.comparing(Dish::getCalories);

        // max 는 맨 뒤 값을 빼는듯
//        menu.stream().collect(maxBy(sort)); 아래와 동일
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
    }
    
    

    static void groupingPractice() {
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return DIET;
                    else if (dish.getCalories() <= 700) return NORMAL;
                    else return FAT;
                })
        );

        /*
            res1 과 res2 의 차이는?
            
            res1은 FISH type인 경우 filter에서 모두 걸러져서(모두 칼로리가 500이하) 
            결과 Map의 Key에 FISH가 아예 존재하지 않는다.
            
            res2는 그룹화 이후 predicate를 적용하기 때문에 FISH Key는 빈 리스트를 value로 갖게 된다.
         */

        Map<Dish.Type, List<Dish>> res1 = menu.stream()
                .filter(d -> d.getCalories() > 500)
                .collect(groupingBy(Dish::getType));

        Map<Dish.Type, List<Dish>> res2 = menu.stream()
                .collect(groupingBy(Dish::getType, filtering(d -> d.getCalories() > 500, toList())));

        /*
            그룹의 각 요리를 관련 이름 목록으로 변환할 수 있다.
         */
        Map<Dish.Type, List<String>> collect2 = menu.stream()
                .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));

        System.out.println("collect2 = " + collect2);
    }

    static void multiLevelGrouping() {
        Map<? extends Class<? extends Dish>, Map<CaloricLevel, List<Dish>>> res1 = 
                menu.stream().collect(
                groupingBy(Dish::getClass,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return DIET;
                            else if (dish.getCalories() <= 700) return NORMAL;
                            else return FAT;
                        })
                )
        );

        System.out.println("res1 = " + res1);

        menu.stream().collect(
                groupingBy(Dish::getType,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), 
                                Optional::get)));

        menu.stream().collect(
                groupingBy(Dish::getType, mapping(dish -> {
                    if (dish.getCalories() <= 400) return DIET;
                    else if (dish.getCalories() <= 700) return NORMAL;
                    else return FAT;
                }, toCollection(HashSet::new)))
        );
    }

    static void partitionPractice() {
        Map<Boolean, Map<Dish.Type, List<Dish>>> res1 = menu.stream().collect(
                partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType))
        );
        System.out.println("res1 = " + res1);

        Map<Boolean, Dish> res2 = menu.stream().collect(
                partitioningBy(
                        Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)
                )
        );
    }
}

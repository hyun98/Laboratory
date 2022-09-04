package Chapter4Stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
//        quiz4_1();
        sliceOptimize();
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

    static void sliceOptimize() {
        
        List<Dish> res1 = menu.stream()
                .filter(d -> d.getCalories() < 320)
                .collect(Collectors.toList());

        // 만약 menu 가 정렬 된 경우라면?
        // 320 보다 작은 메뉴가 나온 경우 반복 작업을 중단하고,
        // 슬라이싱을 통해 가져올 수 있다.
        
        /*
            리스트의 크기가 매우 큰 경우,
            이 작업은 상당한 차이를 보일 수 있다.
         */

        List<Dish> res2 = menu.stream()
                .takeWhile(d -> d.getCalories() < 320)
                .collect(Collectors.toList());
        
        /*
            반대로 현재까지 반복한 요소를 모두 drop하는 방법도 있다.
            predicate가 거짓이 되면 그 지점에서 작업을 중단하고 남은 모든 요소를 반환한다.
         */
        List<Dish> res3 = menu.stream()
                .dropWhile(d -> d.getCalories() < 320)
                .collect(Collectors.toList());
        
        /*
            칼로리가 300 보다 큰 메뉴 중 첫 3개를 반환 
         */
        List<Dish> res4 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        
        /*
            칼로리가 300 보다 큰 메뉴 중 첫 3개를 스킵하고 모두 반환.
            없는 경우 에러 대신 null 반환
         */
        List<Dish> res5 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(3)
                .collect(Collectors.toList());
        
        res5.forEach(System.out::println);  
    }

    static void quiz5_1() {
        List<Dish> res = menu.stream()
                .filter(d -> d.getType() == MEAT)
                .limit(2)
                .collect(Collectors.toList());
    }

    static void streamFlattening() {
        List<String> words = Arrays.asList("Hello", "World");
        List<Object> res = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    static void quiz5_2() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(4, 5);

        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        List<int[]> pairs2 = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

    }

    static void quiz5_3() {
        int cnt = menu.stream()
                .map(d -> 1)
                .reduce(1, Integer::sum);
    }

    static void numberStream() {
        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
    }
}

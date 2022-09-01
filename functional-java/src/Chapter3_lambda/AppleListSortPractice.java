package Chapter3_lambda;

import Chapter1_2.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static Chapter1_2.Color.RED;
import static java.util.Comparator.comparing;

public class AppleListSortPractice {
    
    /*
         sort 시그니처
         void sort(Comparator<? super E> c)
     */

    private static final List<Apple> inventory = new ArrayList<>();

    static {
        List<Integer> weights = Arrays.asList(7, 4, 5, 2, 8, 6, 3, 7, 9, 4, 8, 1);
        for (Integer w : weights) {
            inventory.add(new Apple(w));
        }
    }
    
    
    public static void main(String[] args) {
        // level 1 : 정적인 비교 클래스 생성
        inventory.sort(new AppleComparator());
        
        // level 2 : 익명 클래스로 sort
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        
        // level 3 : 람다 클래스로 sort
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        
        // level 4 : 가독성 향상, 메서드 참조
        inventory.sort(comparing(Apple::getWeight));
        
        inventory.forEach(System.out::println);
        
        // 내림차순
        System.out.println("-----------");

        // 아래 2개의 정렬은 동일함
        
        // reverse 를 사용함
        inventory.sort(comparing(Apple::getWeight).reversed());
        
        // comparator 재정의
        Comparator<Apple> comparator = (a1, a2) -> Integer.compare(a2.getWeight(), a1.getWeight());
        inventory.sort(comparator);

        inventory.forEach(System.out::println);


        // 무게가 같은 경우 color 기준으로 정렬
        inventory.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));
    }
}

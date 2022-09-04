package Chapter5_Stream;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class PracticeCode {

    private static Trader raoul = new Trader("Raoul", "Cambridge");
    private static Trader mario = new Trader("Mario", "Milan");
    private static Trader alan = new Trader("Alan", "Cambridge");
    private static Trader brian = new Trader("Brian", "Cambridge");

    private static final List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );
    
    
    public static void main(String[] args) {
        mission1();
        line();
        mission2();
        line();
        mission3();
        line();
        mission4();
        line();
        mission5();
        line();
        mission6();
        line();
        mission7();
        line();
        mission8();
    }

    static void line() {
        System.out.println("-----------");
    }

    static void mission1() {
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .forEach(System.out::println);
    }

    static void mission2() {
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
        
        // another answer
        // distinct 대신
        Set<String> res2 = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .collect(Collectors.toSet());
    }

    static void mission3() {
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .forEach(System.out::println);
    }

    static void mission4() {
        String res1 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce(" ", (a, b) -> a + b);

        String res2 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(" "));
    }

    static void mission5() {
        boolean isMilan = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(isMilan);
    }

    static void mission6() {
        int cambridgeSum = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
        System.out.println("sum : " + cambridgeSum);
    }

    static void mission7() {
        int maxValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);
        System.out.println(maxValue);
    }

    static void mission8() {
        Optional<Integer> reduce = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        System.out.println(reduce.orElse(-1));
    }
}

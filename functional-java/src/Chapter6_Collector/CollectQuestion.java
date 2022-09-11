package Chapter6_Collector;

import Chapter5_Stream.Trader;
import Chapter5_Stream.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class CollectQuestion {

    private static Trader raoul = new Trader("Raoul", "Cambridge");
    private static Trader mario = new Trader("Mario", "Milan");
    private static Trader alan = new Trader("Alan", "Cambridge");
    private static Trader brian = new Trader("Brian", "Cambridge");

    private static Currency kor = new Currency("KOR");

    private static Currency usd = new Currency("USD");

    private static Currency cdn = new Currency("CDN");


    private static final List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300, kor),
            new Transaction(raoul, 2012, 1000, kor),
            new Transaction(alan, 2011, 1400, kor),
            new Transaction(raoul, 2011, 400, kor),
            new Transaction(mario, 2013, 510, usd),
            new Transaction(brian, 2012, 810, usd),
            new Transaction(mario, 2012, 910, usd),
            new Transaction(mario, 2013, 700, kor),
            new Transaction(raoul, 2012, 1050, cdn),
            new Transaction(alan, 2013, 1150, usd),
            new Transaction(brian, 2013, 2250, cdn),
            new Transaction(alan, 2012, 850, cdn)
    );
    
    /*
        통화별 트랜잭션 그룹화
     */
    public Map<Currency, List<Transaction>> q1() {
        ArrayList<Object> objects = new ArrayList<>();
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency));
    }

    /*
        통화별로 트랜잭션을 그룹화한 다음에 해당 통화로 일어난 모든 트랜잭션 합계를 계산하시오
        (Map<Currency, Integer> 반환)
     */
    
    
    
    /*
        트랜잭션을 비싼 트랜잭션과 저렴한 트랜잭션 두 그룹으로 분류하시오
        (Map<Boolean, List<Transaction>> 반환)
     */
    
    
    /*
        트랜잭션을 도시 등 다수준으로 그룹화 하시오. 그리고 각 트랜잭션이 비싼지 저렴한지 구분하시오
        (Map<String, Map<Boolean, List<Transaction>>> 반환)
     */
}

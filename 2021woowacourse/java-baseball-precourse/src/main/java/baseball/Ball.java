package baseball;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static baseball.Asset.ConstValues.*;
import static java.util.stream.Collectors.toList;

public class Ball {
    
    private String number;

    public Ball() {
        number = generateBallNumber();
    }

    public String generateBallNumber() {
        final List<Integer> selectNumbers = 
                IntStream.range(START_NUM, EXCLUSIVE_END_NUM)
                        .boxed()
                        .collect(toList());

        Collections.shuffle(selectNumbers);
        
        return selectNumbers.subList(0, BALL_LENGTH).stream()
                .map(String::valueOf)
                .reduce(EMPTY_STR, (a, b) -> a + b);
    }
}

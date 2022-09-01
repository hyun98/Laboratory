package test;

import org.junit.jupiter.api.Test;

public class LongObjectConstructionTest {
    @Test
    public void objectLongIntegerTest() {

        // 결과값에 Long, 루프 카운터에 Integer 사용
        Long result = 0L;
        for (Integer i = 0; i < Integer.MAX_VALUE; i++) {
            result += i * 2;
        }
    }

    @Test
    public void objectLongTest() {

        // 결과값에 Long 사용, 루프 카운터에 primitive int 사용
        Long result = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            result += i * 2;
        }
    }

    @Test
    public void primitiveTest() {

        // 둘 다 primitive long, int 사용
        long result = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            result += i * 2;
        }
    }

}

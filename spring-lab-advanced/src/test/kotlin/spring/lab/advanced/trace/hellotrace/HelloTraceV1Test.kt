package spring.lab.advanced.trace.hellotrace

import org.junit.jupiter.api.Test
import spring.lab.advanced.trace.hellotrace.HelloTraceV1

/**
 * 학습용 테스트 코드 입니다.
 */
class HelloTraceV1Test {
    @Test
    fun begin_end() {
        val trace = HelloTraceV1()
        val status = trace.begin("hello")
        trace.end(status)
    }

    @Test
    fun begin_exception() {
        val trace = HelloTraceV1()
        val status = trace.begin("hello")
        trace.exception(status, IllegalStateException())
    }
}
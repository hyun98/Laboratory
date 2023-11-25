package spring.lab.advanced.trace.hellotrace

import org.junit.jupiter.api.Test
import spring.lab.advanced.trace.hellotrace.HelloTraceV2

/**
 * 학습용 테스트 코드 입니다.
 */
class HelloTraceV2Test {
    @Test
    fun begin_end() {
        val trace = HelloTraceV2()
        val status1 = trace.begin("hello1")
        val status2 = trace.beginSync(status1.traceId, "hello2")
        trace.end(status2)
        trace.end(status1)
    }

    @Test
    fun begin_exception() {
        val trace = HelloTraceV2()
        val status1 = trace.begin("hello1")
        val status2 = trace.beginSync(status1.traceId, "hello2")
        trace.exception(status2, IllegalStateException())
        trace.exception(status1, IllegalStateException())
    }
}
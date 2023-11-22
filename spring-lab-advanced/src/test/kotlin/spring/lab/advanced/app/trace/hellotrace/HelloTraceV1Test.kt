package spring.lab.advanced.app.trace.hellotrace

import org.junit.jupiter.api.Test

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
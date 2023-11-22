package spring.lab.advanced.app.v1

import org.springframework.stereotype.Repository
import spring.lab.advanced.app.trace.TraceStatus
import spring.lab.advanced.app.trace.hellotrace.HelloTraceV1
import java.util.Timer

@Repository
class OrderRepositoryV1(
    private val trace: HelloTraceV1
) {
    fun save(itemId: String) {

        var status: TraceStatus? = null

        try {
            status = trace.begin("OrderRepository.save()")

            // 저장 로직
            if (itemId == "ex") {
                // kotlin 은 java 와 다르게 string 에 == 을 사용하면 값의 동등성을 비교한다.
                // kotlin 에서 주소 값을 비교하려면 === 을 사용한다.
                throw IllegalStateException("예외 발생!")
            }

            Thread.sleep(500)

            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }
}
package spring.lab.advanced.app.v2

import org.springframework.stereotype.Repository
import spring.lab.advanced.trace.TraceId
import spring.lab.advanced.trace.TraceStatus
import spring.lab.advanced.trace.hellotrace.HelloTraceV2

@Repository
class OrderRepositoryV2(
    private val trace: HelloTraceV2
) {
    fun save(traceId: TraceId, itemId: String) {

        var status: TraceStatus? = null

        try {
            status = trace.beginSync(traceId, "OrderRepository.save()")

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
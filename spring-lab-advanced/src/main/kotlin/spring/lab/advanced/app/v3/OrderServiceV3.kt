package spring.lab.advanced.app.v3

import org.springframework.stereotype.Service
import spring.lab.advanced.trace.TraceId
import spring.lab.advanced.trace.TraceStatus
import spring.lab.advanced.trace.hellotrace.HelloTraceV2
import spring.lab.advanced.trace.logtrace.LogTrace

@Service
class OrderServiceV3(
    private val orderRepositoryV3: OrderRepositoryV3,
    private val trace: LogTrace
) {
    fun orderItem(itemId: String) {
        var status: TraceStatus? = null

        try {
            status = trace.begin("OrderService.orderItem()")
            orderRepositoryV3.save(itemId)
            trace.end(status)

        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }
}
package spring.lab.advanced.app.v2

import org.springframework.stereotype.Service
import spring.lab.advanced.app.trace.TraceId
import spring.lab.advanced.app.trace.TraceStatus
import spring.lab.advanced.app.trace.hellotrace.HelloTraceV2

@Service
class OrderServiceV2(
    private val orderRepositoryV2: OrderRepositoryV2,
    private val trace: HelloTraceV2
) {
    fun orderItem(traceId: TraceId, itemId: String) {
        var status: TraceStatus? = null

        try {
            status = trace.beginSync(traceId, "OrderService.orderItem()")
            orderRepositoryV2.save(status.traceId, itemId)
            trace.end(status)

        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }
}
package spring.lab.advanced.app.v1

import org.springframework.stereotype.Service
import spring.lab.advanced.trace.TraceStatus
import spring.lab.advanced.trace.hellotrace.HelloTraceV1

@Service
class OrderServiceV1(
    private val orderRepositoryV1: OrderRepositoryV1,
    private val trace: HelloTraceV1
) {
    fun orderItem(itemId: String) {
        var status: TraceStatus? = null

        try {
            status = trace.begin("OrderService.orderItem()")
            orderRepositoryV1.save(itemId)
            trace.end(status)

        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }
}
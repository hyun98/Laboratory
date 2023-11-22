package spring.lab.advanced.app.v2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import spring.lab.advanced.app.trace.TraceStatus
import spring.lab.advanced.app.trace.hellotrace.HelloTraceV2

@RestController
class OrderControllerV2(
    private val orderServiceV2: OrderServiceV2,
    private val trace: HelloTraceV2
) {
    @GetMapping("/v2/request")
    fun request(itemId: String): String {
        var status: TraceStatus? = null

        try {
            status = trace.begin("OrderController.request()")
            orderServiceV2.orderItem(status.traceId, itemId)
            trace.end(status)
            return "ok"

        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }
}
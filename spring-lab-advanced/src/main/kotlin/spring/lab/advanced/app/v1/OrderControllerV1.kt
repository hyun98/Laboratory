package spring.lab.advanced.app.v1

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import spring.lab.advanced.app.trace.TraceId
import spring.lab.advanced.app.trace.TraceStatus
import spring.lab.advanced.app.trace.hellotrace.HelloTraceV1

@RestController
class OrderControllerV1(
    private val orderServiceV1: OrderServiceV1,
    private val trace: HelloTraceV1
) {
    @GetMapping("/v1/request")
    fun request(itemId: String): String {
        var status: TraceStatus? = null

        try {
            status = trace.begin("OrderController.request()")
            orderServiceV1.orderItem(itemId)
            trace.end(status)
            return "ok"

        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }
}
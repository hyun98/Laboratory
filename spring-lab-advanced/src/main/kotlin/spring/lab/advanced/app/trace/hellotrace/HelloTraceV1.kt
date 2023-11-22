package spring.lab.advanced.app.trace.hellotrace

import mu.KLogger
import mu.KotlinLogging
import org.springframework.stereotype.Component
import spring.lab.advanced.app.trace.TraceId
import spring.lab.advanced.app.trace.TraceStatus
import java.lang.StringBuilder

@Component
class HelloTraceV1 {
    private val log: KLogger = KotlinLogging.logger {}

    companion object {
        const val START_PREFIX = "-->"
        const val COMPLETE_PREFIX = "<--"
        const val EX_PREFIX = "<X-"
    }

    fun begin(message: String): TraceStatus {
        val traceId = TraceId()
        val startTimeMs = System.currentTimeMillis()
        log.info { "[${traceId.id}] ${addSpace(START_PREFIX, traceId.level)}${message}" }
        return TraceStatus(traceId, startTimeMs, message)
    }

    fun end(status: TraceStatus) {
        complete(status, null)
    }

    fun exception(status: TraceStatus, e: Exception) {
        complete(status, e)
    }

    private fun complete(status: TraceStatus, e: Exception?) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs = stopTimeMs - status.startTimeMs
        val traceId = status.traceId

        if (e == null) {
            log.info {
                "[${traceId.id}] ${addSpace(COMPLETE_PREFIX, traceId.level)}${status.message} time=${resultTimeMs}ms"
            }
        } else {
            log.info {
                "[${traceId.id}] ${addSpace(EX_PREFIX, traceId.level)}${status.message} time=${resultTimeMs}ms ex=${e}"
            }
        }

    }

    private fun addSpace(prefix: String, level: Int): String {
        val sb = StringBuilder()
        for (i: Int in 0 until level) {
            sb.append(if (i == level - 1) "|" else "|   ")
        }
        return sb.toString()
    }
}
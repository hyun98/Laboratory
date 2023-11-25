package spring.lab.advanced.trace.logtrace

import mu.KLogger
import mu.KotlinLogging
import spring.lab.advanced.trace.TraceId
import spring.lab.advanced.trace.TraceStatus
import java.lang.StringBuilder


class FieldLogTrace: LogTrace {
    private val log: KLogger = KotlinLogging.logger {}

    // traceId 동기화, 동시성 이슈 발생함
    private var traceIdHolder: TraceId? = null

    companion object {
        const val START_PREFIX = "-->"
        const val COMPLETE_PREFIX = "<--"
        const val EX_PREFIX = "<X-"
    }

    override fun begin(message: String): TraceStatus {
        syncTraceId()

        val traceId = traceIdHolder!!
        val startTimeMs = System.currentTimeMillis()
        log.info { "[${traceId.id}] ${addSpace(START_PREFIX, traceId.level)}${message}" }
        return TraceStatus(traceId, startTimeMs, message)
    }

    override fun end(status: TraceStatus) {
        complete(status, null)
    }

    override fun exception(status: TraceStatus, e: Exception) {
        complete(status, e)
    }

    private fun syncTraceId() {
        if (traceIdHolder == null) {
            traceIdHolder = TraceId()
        } else {
            traceIdHolder = traceIdHolder!!.createNextId()
        }
    }

    private fun releaseTraceId() {
        if (traceIdHolder!!.isFirstLevel()) {
            traceIdHolder = null // dispose
        } else {
            traceIdHolder = traceIdHolder!!.createPreviousId()
        }
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

        releaseTraceId()
    }

    private fun addSpace(prefix: String, level: Int): String {
        val sb = StringBuilder()
        for (i: Int in 0 until level) {
            sb.append(if (i == level - 1) "|${prefix}" else "|   ")
        }
        return sb.toString()
    }
}
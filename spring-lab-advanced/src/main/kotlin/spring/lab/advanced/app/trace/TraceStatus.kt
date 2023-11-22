package spring.lab.advanced.app.trace

data class TraceStatus(
    val traceId: TraceId,
    val startTimeMs: Long,
    val message: String
)

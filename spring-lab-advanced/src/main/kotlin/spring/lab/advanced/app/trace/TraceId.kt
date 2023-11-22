package spring.lab.advanced.app.trace

import java.util.UUID

data class TraceId(
    val id: String = createId(),
    val level: Int = 0
) {
    fun createNextId(): TraceId {
        return TraceId(id, level + 1)
    }

    fun createPreviousId(): TraceId {
        return TraceId(id, level - 1)
    }

    fun isFirstLevel(): Boolean {
        return level == 0
    }
}

private fun createId(): String {
    return UUID.randomUUID().toString().substring(0, 8)
}

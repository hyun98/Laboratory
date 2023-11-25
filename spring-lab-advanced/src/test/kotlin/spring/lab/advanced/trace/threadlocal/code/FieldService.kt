package spring.lab.advanced.trace.threadlocal.code

import mu.KLogger
import mu.KotlinLogging

class FieldService {
    private val log: KLogger = KotlinLogging.logger {}

    private var nameStore: String = "null"

    fun logic(name: String): String {
        log.info { "저장 시작 name=${name} -> nameStore=${nameStore}" }
        nameStore = name
        sleep(1000)
        log.info { "조회 nameStore=${nameStore}" }
        return nameStore
    }

    private fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
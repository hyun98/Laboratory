package spring.lab.advanced.trace.strategy.code.template

import mu.KotlinLogging

class TimeLogTemplate {
    private val log = KotlinLogging.logger {}

    fun execute(callback: Callback) {
        val startTime = System.currentTimeMillis()
        callback.call()

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info { "resultTime=${resultTime}ms" }
    }

}
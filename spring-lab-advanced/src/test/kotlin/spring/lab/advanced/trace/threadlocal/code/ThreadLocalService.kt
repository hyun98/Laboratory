package spring.lab.advanced.trace.threadlocal.code

import mu.KLogger
import mu.KotlinLogging

class ThreadLocalService {
    private val log: KLogger = KotlinLogging.logger {}

    private var nameStore: ThreadLocal<String> = ThreadLocal()

    fun logic(name: String): String {
        log.info { "저장 시작 name=${name} -> nameStore=${nameStore}" }
        nameStore.set(name)
        sleep(1000)
        log.info { "조회 nameStore=${nameStore.get()}" }
        return nameStore.get()
    }

    private fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
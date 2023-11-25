package spring.lab.advanced.trace.threadlocal

import mu.KotlinLogging
import org.junit.jupiter.api.Test
import spring.lab.advanced.trace.threadlocal.code.ThreadLocalService
import java.lang.Thread.sleep

class ThreadLocalServiceTest {
    private val log = KotlinLogging.logger {}

    private val threadLocalService = ThreadLocalService()

    @Test
    fun field() {
        log.info { "main start" }
        val userA = Runnable {
            threadLocalService.logic("userA")
        }

        val userB = Runnable {
            threadLocalService.logic("userB")
        }

        val threadA = Thread(userA)
        threadA.name = "thread-A"
        val threadB = Thread(userB)
        threadB.name = "thread-B"

        threadA.start()
        sleep(100)
        threadB.start()

        sleep(2000)
        log.info { "main exit" }
    }
}
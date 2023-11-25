package spring.lab.advanced.trace.threadlocal

import mu.KotlinLogging
import org.junit.jupiter.api.Test
import spring.lab.advanced.trace.threadlocal.code.FieldService
import java.lang.Thread.sleep

class FieldServiceTest {
    private val log = KotlinLogging.logger {}

    private val fieldService = FieldService()

    @Test
    fun field() {
        log.info { "main start" }
        val userA = Runnable {
            fieldService.logic("userA")
        }

        val userB = Runnable {
            fieldService.logic("userB")
        }

        val threadA = Thread(userA)
        threadA.name = "thread-A"
        val threadB = Thread(userB)
        threadB.name = "thread-B"

        threadA.start()
        sleep(100)
        threadB.start()

        sleep(2000)
    }
}
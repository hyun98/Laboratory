package spring.lab.advanced.proxy.decorator

import mu.KotlinLogging
import org.junit.jupiter.api.Test

class DecoratorPatternTest {
    private val log = KotlinLogging.logger {  }

    @Test
    fun decorator() {
        val realComponent = RealComponent()
        val messageDecorator = MessageDecorator(realComponent)
        val timeDecorator = TimeDecorator(messageDecorator)
        val result = timeDecorator.operation()
        log.info { "result=${result}" }
    }
}
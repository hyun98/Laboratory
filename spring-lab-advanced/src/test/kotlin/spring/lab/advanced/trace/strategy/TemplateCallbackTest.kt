package spring.lab.advanced.trace.strategy

import mu.KotlinLogging
import org.junit.jupiter.api.Test
import spring.lab.advanced.trace.strategy.code.template.Callback
import spring.lab.advanced.trace.strategy.code.template.TimeLogTemplate

class TemplateCallbackTest {
    private val log = KotlinLogging.logger {}

    @Test
    fun callbackV1() {
        val template = TimeLogTemplate()
        val call1 = Callback { log.info { "start logic1" } }
        val call2 = Callback { log.info { "start logic2" } }

        template.execute(call1)
        template.execute(call2)
    }

    @Test
    fun callbackV2() {
        val template = TimeLogTemplate()
        template.execute { log.info { "start logic1" } }
        template.execute { log.info { "start logic2" } }
    }
}
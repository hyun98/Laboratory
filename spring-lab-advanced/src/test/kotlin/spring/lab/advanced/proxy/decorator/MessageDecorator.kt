package spring.lab.advanced.proxy.decorator

import mu.KotlinLogging

class MessageDecorator(
    private val component: Component
) : Component {
    private val log = KotlinLogging.logger { }

    override fun operation(): String {
        log.info { "MessageDecorator 실행" }

        val result = component.operation()
        val decoResult = "****${result}****"
        log.info { "MessageDecorator 전=${result} 후=${decoResult}" }

        return decoResult
    }

}
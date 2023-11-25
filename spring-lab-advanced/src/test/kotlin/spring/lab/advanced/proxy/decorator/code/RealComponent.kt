package spring.lab.advanced.proxy.decorator.code

import mu.KotlinLogging

class RealComponent : Component {
    private val log = KotlinLogging.logger {  }

    override fun operation(): String {
        log.info { "RealComponent 실행" }
        return "data"
    }

}
package spring.lab.advanced.proxy.common.service

import mu.KotlinLogging

private val log = KotlinLogging.logger {  }

class ServiceImpl : ServiceInterface {
    override fun save() {
        log.info { "save 호출" }
    }

    override fun find() {
        log.info { "find 호출" }
    }
}
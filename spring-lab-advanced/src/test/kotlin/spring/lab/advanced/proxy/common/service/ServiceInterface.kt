package spring.lab.advanced.proxy.common.service

import org.springframework.stereotype.Service

@Service
interface ServiceInterface {
    fun save()

    fun find()
}
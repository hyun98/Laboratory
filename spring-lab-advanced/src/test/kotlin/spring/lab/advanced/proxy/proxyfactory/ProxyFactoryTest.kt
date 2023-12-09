package spring.lab.advanced.proxy.proxyfactory

import mu.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.AopUtils
import spring.lab.advanced.proxy.common.advice.TimeAdvice
import spring.lab.advanced.proxy.common.service.ConcreteService
import spring.lab.advanced.proxy.common.service.ServiceImpl
import spring.lab.advanced.proxy.common.service.ServiceInterface


private val log = KotlinLogging.logger {  }

class ProxyFactoryTest {
    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    fun interfaceProxy() {
        val target = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.addAdvice(TimeAdvice())
        val proxy = proxyFactory.proxy as ServiceInterface

        log.info("targetClass={}", target.javaClass)
        log.info("proxyClass={}", proxy.javaClass)

        proxy.save()

        assertThat(AopUtils.isAopProxy(proxy)).isTrue()
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue()
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse()
    }

    @Test
    @DisplayName("구체 클래스만 있으면 CGLIB 사용")
    fun concreteProxy() {
        val target = ConcreteService()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.addAdvice(TimeAdvice())
        val proxy = proxyFactory.proxy as ConcreteService
        log.info("targetClass={}", target.javaClass)
        log.info("proxyClass={}", proxy.javaClass)

        proxy.call()

        assertThat(AopUtils.isAopProxy(proxy)).isTrue()
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse()
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue()
    }

    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고, 클래스 기반 프록시 사용")
    fun proxyTargetClass() {
        val target = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.isProxyTargetClass = true;
        proxyFactory.addAdvice(TimeAdvice())
        val proxy = proxyFactory.proxy as ServiceInterface

        log.info("targetClass={}", target.javaClass)
        log.info("proxyClass={}", proxy.javaClass)

        proxy.save()

        assertThat(AopUtils.isAopProxy(proxy)).isTrue()
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse()
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue()
    }
}
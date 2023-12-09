package spring.lab.advanced.proxy.reflection

import mu.KotlinLogging
import org.junit.jupiter.api.Test
import java.lang.reflect.Method


private val log = KotlinLogging.logger {  }

class ReflectionTest {

    @Test
    fun reflection0() {
        val target = Hello()

        log.info { "start" }
        val result1 = target.callA()
        log.info { "result1 = $result1" }

        log.info { "start" }
        val result2 = target.callB()
        log.info { "result2 = $result2" }
    }

    class Hello {
        fun callA(): String {
            log.info { "callA" }
            return "A"
        }

        fun callB(): String {
            log.info { "callB" }
            return "B"
        }
    }

    @Test
    @Throws(Exception::class)
    fun reflection1() {
        val classHello =
            Class.forName(
                "spring.lab.advanced.proxy.reflection.ReflectionTest\$Hello"
            )

        val target = Hello()
        val methodCallA = classHello.getMethod("callA")
        val result1 = methodCallA.invoke(target)
        log.info { "result1 = $result1" }

        val methodCallB = classHello.getMethod("callB")
        val result2 = methodCallB.invoke(target)
        log.info { "result2 = $result2" }
    }

    @Test
    @Throws(Exception::class)
    fun reflection2() {
        val classHello =
            Class.forName(
                "spring.lab.advanced.proxy.reflection.ReflectionTest\$Hello"
            )

        val target = Hello()
        val methodCallA = classHello.getMethod("callA")
        dynamicCall(methodCallA, target)

        val methodCallB = classHello.getMethod("callB")
        val result2 = methodCallB.invoke(target)
        log.info { "result2 = $result2" }
    }

    private fun dynamicCall(method: Method, target: Any) {
        log.info { "start" }
        val result = method.invoke(target)
        log.info { "result = $result" }
    }
}
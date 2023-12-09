package spring.lab.advanced.proxy.common.service

import mu.KotlinLogging
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

/**
 * CGLIB는 상속을 기반으로 프록시 기술을 사용하기 때문에 final class는 프록시를 사용할 수 없어 에러가 발생하게 된다.
 * 코틀린 클래스를 자바 클래스로 변환하면 클래스 및 메서드들에 모두 final 키워드가 붙어있다.
 * 그래서 코틀린에서는 클래스와 멤버를 상속하거나 오버라이드하기 위해서는 open 키워드를 명시해줘야 한다.
 * All-open 플러그인은 특정 어노테이션이 붙어있는 클래스와 멤버를 모두 open 키워드를 붙여주는 플러그인이다.
 *
 * Spring 은
 * @Component
 * @Async
 * @Transactional
 * @Cacheable
 * @SpringBootTest
 * 위와 같은 특정 어노테이션에 대해서 All-open이 적용되어있다.
 * 따라서 kotlin에서 특정 객체를 CGLIB 를 사용해서 프록시 팩토리에 할당해 주고 싶다면 All-open 플러그인을 따로 사용해주어야 한다.
 * All-open 플러그인은 특정 어노테이션이 붙어있는 클래스와 멤버를 모두 open 키워드를 붙여주는 플러그인이다.
 *
 * 따로 플러그인을 설치해서 어노테이션을 만들지 않고, 그냥 Spring 에서 제공하는 어노테이션을 붙여주었다.
 */
@Component
class ConcreteService {
    fun call() {
        log.info("ConcreteService 호출")
    }
}
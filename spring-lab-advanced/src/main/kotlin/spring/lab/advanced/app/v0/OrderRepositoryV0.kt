package spring.lab.advanced.app.v0

import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV0 {
    fun save(itemId: String) {
        // 저장 로직
        if (itemId == "ex") {
            // kotlin 은 java 와 다르게 string 에 == 을 사용하면 값의 동등성을 비교한다.
            // kotlin 에서 주소 값을 비교하려면 === 을 사용한다.
            throw IllegalStateException("예외 발생!")
        }
    }
}
package spring.lab.advanced

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import spring.lab.advanced.trace.logtrace.FieldLogTrace
import spring.lab.advanced.trace.logtrace.LogTrace
import spring.lab.advanced.trace.logtrace.ThreadLocalLogTrace

@Configuration
class LogTraceConfig {
    @Bean
    fun logTrace(): LogTrace {
        return ThreadLocalLogTrace()
    }
}
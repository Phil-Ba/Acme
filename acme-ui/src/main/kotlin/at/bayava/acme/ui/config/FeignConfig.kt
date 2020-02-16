package at.bayava.acme.ui.config

import com.fasterxml.jackson.databind.ObjectMapper
import feign.codec.ErrorDecoder
import mu.KotlinLogging
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.hateoas.support.WebStack

private val logger = KotlinLogging.logger {}

@Configuration
@EnableHypermediaSupport(
    type = [EnableHypermediaSupport.HypermediaType.HAL, EnableHypermediaSupport.HypermediaType.COLLECTION_JSON, EnableHypermediaSupport.HypermediaType.UBER, EnableHypermediaSupport.HypermediaType.HAL_FORMS],
    stacks = [WebStack.WEBMVC, WebStack.WEBFLUX]
)
@EnableFeignClients("at.bayava.acme.ui.client.rest")
class FeignConfig(objectMapper: ObjectMapper) {

    @Bean
    fun errorHanlder(): ErrorDecoder {
        return ErrorDecoder { s, response ->
            logger.error("Error while calling rest:\r\n$response")
            Exception(response.reason())
        }
    }


}

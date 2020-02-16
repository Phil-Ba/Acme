package at.bayava.acme.ui.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients("at.bayava.acme.ui.client.rest")
class FeignConfig

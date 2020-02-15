package at.bayava.acme.productinventory.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients("at.bayava.acme.productinventory.client.rest")
class FeignConfig

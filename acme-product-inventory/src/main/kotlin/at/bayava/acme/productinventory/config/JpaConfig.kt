package at.bayava.acme.productinventory.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["at.bayava.acme.productinventory.db"])
class JpaConfig

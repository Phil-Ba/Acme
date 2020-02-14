package at.bayava.acme.categories.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackages = ["at.bayava.acme.categories.db.model"])
@EnableJpaRepositories(basePackages = ["at.bayava.acme.categories.db"])
class JpaConfig

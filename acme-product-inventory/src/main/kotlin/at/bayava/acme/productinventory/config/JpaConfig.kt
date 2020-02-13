package at.bayava.acme.productinventory.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackages = ["at.bayava.acme.productinventory.db.model"])
@EnableJpaRepositories(basePackages = ["at.bayava.acme.productinventory.db"])
class JpaConfig

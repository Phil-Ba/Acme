package at.bayava.acme.productinventory.config

import at.bayava.acme.productinventory.db.model.ProductItem
import at.bayava.acme.productinventory.db.model.ProductType
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer

@Configuration
class SpringDataRestConfig : RepositoryRestConfigurer {

    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration) {
        config.exposeIdsFor(ProductType::class.java, ProductItem::class.java)
    }
}
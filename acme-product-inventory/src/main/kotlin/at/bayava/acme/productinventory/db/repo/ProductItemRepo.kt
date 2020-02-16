package at.bayava.acme.productinventory.db.repo

import at.bayava.acme.productinventory.db.model.ProductItem
import at.bayava.acme.productinventory.rest.model.InlineProductType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(excerptProjection = InlineProductType::class)
interface ProductItemRepo : JpaRepository<ProductItem, String>
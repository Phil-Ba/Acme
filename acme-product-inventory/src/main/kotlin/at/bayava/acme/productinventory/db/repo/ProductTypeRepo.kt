package at.bayava.acme.productinventory.db.repo

import at.bayava.acme.productinventory.db.model.ProductType
import org.springframework.data.jpa.repository.JpaRepository

interface ProductTypeRepo : JpaRepository<ProductType, Long>
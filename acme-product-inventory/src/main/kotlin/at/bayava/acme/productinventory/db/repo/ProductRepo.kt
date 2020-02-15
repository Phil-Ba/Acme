package at.bayava.acme.productinventory.db.repo

import at.bayava.acme.productinventory.db.model.ProductItem
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepo : JpaRepository<ProductItem, String>
package at.bayava.acme.productinventory.db.repo

import at.bayava.acme.productinventory.db.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepo : JpaRepository<Product, String>
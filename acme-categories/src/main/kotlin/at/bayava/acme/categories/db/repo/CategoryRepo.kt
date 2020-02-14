package at.bayava.acme.categories.db.repo

import at.bayava.acme.categories.db.model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepo : JpaRepository<Category, Long>
package at.bayava.acme.categories.service

import at.bayava.acme.productinventory.client.model.Category
import at.bayava.acme.productinventory.client.rest.CategoryClient
import mu.KotlinLogging
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class CategoryService(val categoryClient: CategoryClient) {

    @Cacheable("categories-cache")
    fun fetchCategories(): Collection<Category> {
        logger.info("Reloading categories.")
        return categoryClient.fetchCategories().content
    }

}
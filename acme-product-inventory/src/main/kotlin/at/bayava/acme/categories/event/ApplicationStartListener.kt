package at.bayava.acme.productinventory.event

import at.bayava.acme.categories.service.CategoryService
import at.bayava.acme.productinventory.db.model.ProductType
import at.bayava.acme.productinventory.db.repo.ProductTypeRepo
import mu.KotlinLogging
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.util.*

private val logger = KotlinLogging.logger {}

@Component
@Profile("!test")
class ApplicationStartListener(val productTypeRepo: ProductTypeRepo, val categoryService: CategoryService) :
    ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        val scanner = Scanner(this::class.java.getResourceAsStream("/products.csv"))
            .useDelimiter(";|\\r?\\n|\\r")
        scanner.nextLine()

        val categoryNames = categoryService.fetchCategories().associate { Pair(it.name, it.name) }
        val products = mutableListOf<ProductType>()
        while (scanner.hasNextLine()) {
            val name = scanner.next()
            val categoryName = scanner.next()
            val productType = ProductType(-1, name, categoryName, emptyList())
            logger.info("Created productType[$productType]")
            products += productType
        }

        productTypeRepo.saveAll(products)
    }


}
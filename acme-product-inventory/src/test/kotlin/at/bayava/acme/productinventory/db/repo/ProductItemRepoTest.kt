package at.bayava.acme.productinventory.db.repo

import at.bayava.acme.categories.service.CategoryService
import at.bayava.acme.productinventory.client.model.Category
import at.bayava.acme.productinventory.db.model.ProductItem
import at.bayava.acme.productinventory.db.model.ProductType
import at.bayava.acme.test.SpringBaseSpec
import io.kotlintest.matchers.collections.shouldBeEmpty
import io.kotlintest.matchers.collections.shouldContainExactly
import io.mockk.every
import io.mockk.mockkClass
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Transactional
@ContextConfiguration(classes = [ProductItemRepoTest.Config::class])
class ProductItemRepoTest : SpringBaseSpec() {

    open class Config {
        @Bean
        @Primary
        open fun categoryServiceMock(): CategoryService {
            val categoryService = mockkClass(CategoryService::class)
            every { categoryService.fetchCategories() } returns listOf(Category(1, "testProductType", 1.0, 2.0, 3.0))
            return categoryService
        }
    }

    @Autowired
    lateinit var productRepo: ProductRepo

    @Test
    fun initialDbShouldBeEmpty() {
        val result = productRepo.findAll()

        result.shouldBeEmpty()
    }

    @Test
    fun afterInsertEntryShouldBeInDb() {
        val productType = ProductType(-1, "testName", "testProductType", emptyList())
        val productItem = ProductItem(
            -1,
            productType,
            LocalDate.of(1990, 12, 1),
            123.45
        )

        val product = productRepo.save(productItem)
        productRepo.flush()

        val result = productRepo.findAll()

        result.shouldContainExactly(product)
    }

}
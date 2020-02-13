package at.bayava.acme.productinventory.db.repo

import SpringBaseSpec
import at.bayava.acme.productinventory.db.model.Product
import io.kotlintest.matchers.collections.shouldBeEmpty
import io.kotlintest.matchers.collections.shouldContainExactly
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Transactional
class ProductRepoTest : SpringBaseSpec() {
    @Autowired
    lateinit var productRepo: ProductRepo

    @Test
    fun initialDbShouldBeEmpty() {
        val result = productRepo.findAll()

        result.shouldBeEmpty()
    }

    @Test
    fun afterInsertEntryShouldBeInDb() {
        val product = productRepo.save(Product("testProduct", LocalDate.of(1990, 12, 1), 123.45))
        productRepo.flush()
        val result = productRepo.findAll()

        result.shouldContainExactly(product)
    }

}
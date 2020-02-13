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

    init {
        "findAll"{
            should("return initially an empty list") {
                val result = productRepo.findAll()

                result.shouldBeEmpty()
            }
            "asd"{
                should("return the item after inserting it") {
                    val product = productRepo.save(Product("testProduct", LocalDate.of(1990, 12, 1), 123.45))
                    productRepo.flush()
                    val result = productRepo.findAll()

                    result.shouldBeEmpty()
                    result.shouldContainExactly(product)
                }
            }
            should("return an empty list again since testtransactions are rolled back") {
                val result = productRepo.findAll()

                result.shouldBeEmpty()
            }
        }
    }

}
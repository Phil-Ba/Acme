package at.bayava.acme.categoryinventory.db.repo

import at.bayava.acme.categories.db.model.Category
import at.bayava.acme.categories.db.repo.CategoryRepo
import at.bayava.acme.test.SpringBaseSpec
import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import javax.validation.ConstraintViolationException

@Transactional
class CategoryRepoTest : SpringBaseSpec() {
    @Autowired
    lateinit var categoryRepo: CategoryRepo

    @Test
    fun afterInsertEntryShouldBeInDb() {
        val category = categoryRepo.save(Category(-1, "testcategory", 0.1, 0.2,0.3))
        categoryRepo.flush()
        val result = categoryRepo.findAll()

        result.shouldContain(category)
        val idx = result.indexOf(category)
        result[idx].id shouldNotBe -1
    }

    @Test(ConstraintViolationException::class)
    fun insertShouldFailWithInvalidLowTopRange() {
        categoryRepo.save(Category(-1, "testcategory", 500.1, 0.2, 0.3))
        categoryRepo.flush()
    }

}
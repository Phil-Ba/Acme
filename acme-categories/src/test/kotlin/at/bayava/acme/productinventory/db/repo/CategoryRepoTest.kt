package at.bayava.acme.categoryinventory.db.repo

import at.bayava.acme.categories.db.model.Category
import at.bayava.acme.categories.db.repo.CategoryRepo
import at.bayava.acme.test.SpringBaseSpec
import io.kotlintest.matchers.collections.shouldBeEmpty
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class CategoryRepoTest : SpringBaseSpec() {
    @Autowired
    lateinit var categoryRepo: CategoryRepo

    @Test
    fun initialDbShouldBeEmpty() {
        val result = categoryRepo.findAll()

        result.shouldBeEmpty()
    }

    @Test
    fun afterInsertEntryShouldBeInDb() {
        val category = categoryRepo.save(Category(-1, "testcategory", 0.1, 0.2))
        categoryRepo.flush()
        val result = categoryRepo.findAll()

        result.shouldContainExactly(category)
        result[0].id shouldNotBe -1
    }

}
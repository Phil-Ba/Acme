package at.bayava.acme.categories.validation

import at.bayava.acme.categories.db.model.Category
import io.kotlintest.properties.Gen
import io.kotlintest.properties.assertAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.ShouldSpec
import io.mockk.mockk

class CategoryLimitValidatorTest : ShouldSpec() {
    init {
        val doubleOrNullGen = Gen.positiveDoubles().orNull()
        val doubleGen = Gen.positiveDoubles()
        val validCategoryGen: Gen<Category> = Gen.bind(doubleGen, doubleOrNullGen) { d, dOrNull ->
            if (dOrNull == null) {
                return@bind Category(-1, "CatName", d, dOrNull)
            }
            when (d.compareTo(dOrNull)) {
                1 -> Category(-1, "CatName", dOrNull, d)
                0 -> Category(-1, "CatName", d, dOrNull - 0.01)
                -1 -> Category(-1, "CatName", d, dOrNull)
                else -> throw IllegalArgumentException()
            }
        }
        "for all valid Categories"{
            should("isValid should return true") {
                assertAll(validCategoryGen) { category: Category ->
                    CategoryLimitValidator().isValid(
                        category,
                        mockk(relaxed = true)
                    ) shouldBe true
                }
            }
        }

        val invalidCategoryGen: Gen<Category> = Gen.bind(doubleGen, doubleGen) { d1, d2 ->
            when (d1.compareTo(d2)) {
                1 -> Category(-1, "CatName", d1, d2)
                0 -> Category(-1, "CatName", d1, d2)
                -1 -> Category(-1, "CatName", d2, d1)
                else -> throw IllegalArgumentException()
            }
        }
        "for all invalid Categories"{
            should("isValid should return false") {
                assertAll(invalidCategoryGen) { category: Category ->
                    CategoryLimitValidator().isValid(
                        category,
                        mockk(relaxed = true)
                    ) shouldBe false
                }
            }
        }
    }

}
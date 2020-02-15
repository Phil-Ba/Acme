package at.bayava.acme.productinventory.validation

import at.bayava.acme.categories.service.CategoryService
import at.bayava.acme.productinventory.db.model.ProductType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Configurable
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Configurable
class CategoryValidator : ConstraintValidator<ValidCategory, ProductType> {

    @Autowired
    lateinit var categoryService: CategoryService

    override fun isValid(productType: ProductType, context: ConstraintValidatorContext): Boolean {
        val categoryNames = categoryService.fetchCategories().associate { Pair(it.name, it.name) }
        val valid = categoryNames.containsKey(productType.categoryName)
        if (!valid) {
            context.buildConstraintViolationWithTemplate("Unknown category[${productType.categoryName}]!")
                .addConstraintViolation()
        }
        return valid
    }

}
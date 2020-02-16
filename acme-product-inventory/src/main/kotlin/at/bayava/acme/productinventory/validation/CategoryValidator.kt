package at.bayava.acme.productinventory.validation

import at.bayava.acme.categories.service.CategoryService
import at.bayava.acme.productinventory.db.model.ProductType
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class CategoryValidator(val categoryService: CategoryService) : ConstraintValidator<ValidCategory, ProductType> {

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
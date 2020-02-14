package at.bayava.acme.categories.validation

import at.bayava.acme.categories.db.model.Category
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class CategoryLimitValidator : ConstraintValidator<ValidCategoryLimit, Category> {

    override fun isValid(category: Category, context: ConstraintValidatorContext): Boolean {
        val low = category.low
        val top = category.top
        if (top != null && low >= top) {
            context.buildConstraintViolationWithTemplate("Low must be a lower value than top!")
                .addConstraintViolation()
            return false
        }
        return true
    }

}
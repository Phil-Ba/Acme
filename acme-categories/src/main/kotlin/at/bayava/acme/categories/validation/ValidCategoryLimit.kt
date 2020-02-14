package at.bayava.acme.categories.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Constraint(validatedBy = [CategoryLimitValidator::class])
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidCategoryLimit constructor(
    val message: String = "Invalid Low-Top range!",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
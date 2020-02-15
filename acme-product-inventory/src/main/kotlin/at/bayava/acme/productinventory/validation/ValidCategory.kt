package at.bayava.acme.productinventory.validation


import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Constraint(validatedBy = [CategoryValidator::class])
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidCategory constructor(
    val message: String = "Invalid category!",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
package at.bayava.acme.ui.model.rest

import java.time.LocalDate

data class ProductItem(
    var id: Long?,
    var productType: ProductType?,
    var deliveryDate: LocalDate,
    var declaredValue: Double
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductItem

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
package at.bayava.acme.ui.model.rest

import java.time.LocalDate

data class ProductItem(
    var id: Long,
    var productType: String,
    var deliveryDate: LocalDate,
    var declaredValue: Double
)
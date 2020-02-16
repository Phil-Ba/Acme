package at.bayava.acme.ui.model.rest

import java.time.LocalDate

data class ProductItemPostDto(
    var id: Long?,
    var productTypeId: Long,
    var deliveryDate: LocalDate,
    var declaredValue: Double
)


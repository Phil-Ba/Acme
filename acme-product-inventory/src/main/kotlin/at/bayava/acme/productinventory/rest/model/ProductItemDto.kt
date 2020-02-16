package at.bayava.acme.productinventory.rest.model

import com.sun.istack.NotNull
import java.time.LocalDate

data class ProductItemDto(
    var id: Long?,
    @NotNull
    var productTypeId: Long,
    @NotNull
    var deliveryDate: LocalDate,
    @NotNull
    var declaredValue: Double
)


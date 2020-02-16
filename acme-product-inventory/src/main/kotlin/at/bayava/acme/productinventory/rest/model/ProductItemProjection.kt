package at.bayava.acme.productinventory.rest.model

import at.bayava.acme.productinventory.db.model.ProductItem
import at.bayava.acme.productinventory.db.model.ProductType
import org.springframework.data.rest.core.config.Projection
import java.time.LocalDate

@Projection(name = "inlineType", types = [ProductItem::class])
interface InlineProductType {
    val id: Long
    val productType: ProductType
    val deliveryDate: LocalDate
    val declaredValue: Double
}

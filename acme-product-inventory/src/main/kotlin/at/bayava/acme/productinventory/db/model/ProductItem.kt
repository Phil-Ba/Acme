package at.bayava.acme.productinventory.db.model

import com.sun.istack.NotNull
import java.time.LocalDate
import javax.persistence.*

@Entity
data class ProductItem(
    @Id
    @SequenceGenerator(name = "PRODCUT_SEQ", sequenceName = "PRODUCT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ")
    var id: Long,
    @NotNull
    @ManyToOne(optional = false)
    var productType: ProductType,
    @NotNull
    var deliveryDate: LocalDate,
    @NotNull
    var declaredValue: Double
) {
    constructor() : this(-1, ProductType(), LocalDate.MIN, 0.0)

}

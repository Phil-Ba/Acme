package at.bayava.acme.productinventory.db.model

import com.sun.istack.NotNull
import java.time.LocalDate
import javax.persistence.*
import javax.validation.Valid

@Entity
data class ProductItem(
    @Id
    @SequenceGenerator(name = "PRODCUT_SEQ", sequenceName = "PRODUCT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ")
    var id: Long,
    @Valid
    @NotNull
    @ManyToOne(optional = false, cascade = [CascadeType.ALL])
    var productType: ProductType? = null
    @NotNull
    var deliveryDate: LocalDate = LocalDate.MIN
    @NotNull
    var declaredValue: Double = 0.0

    constructor(
        id: Long,
        productType: ProductType,
        deliveryDate: LocalDate,
        declaredValue: Double
    ) : this() {
        this.id = id
        this.productType = productType
        this.deliveryDate = deliveryDate
        this.declaredValue = declaredValue
    }
}

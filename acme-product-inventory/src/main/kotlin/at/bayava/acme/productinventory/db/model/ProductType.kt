package at.bayava.acme.productinventory.db.model

import at.bayava.acme.productinventory.validation.ValidCategory
import com.sun.istack.NotNull
import javax.persistence.*

@Entity
@ValidCategory
data class ProductType(
    @Id
    @SequenceGenerator(name = "PRODUCT_TYPE_SEQ", sequenceName = "PRODUCT_TYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROCUCT_TYPE_SEQ")
    var id: Long,
    @Column(unique = true)
    var name: String,
    @NotNull
    var categoryName: String,
    @OneToMany
    var productItems: List<ProductItem>
) {
    constructor() : this(-1, "", "", emptyList())
}

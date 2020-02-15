package at.bayava.acme.productinventory.db.model

import com.sun.istack.NotNull
import javax.persistence.*

@Entity
data class ProductType(
    @Id
    @SequenceGenerator(name = "PRODUCT_TYPE_SEQ", sequenceName = "PRODUCT_TYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROCUCT_TYPE_SEQ")
    var id: Long,
    @NotNull
    var categoryName: String,
    @OneToMany
    var productItems: List<ProductItem>
) {
    constructor() : this(-1, "", emptyList())
}

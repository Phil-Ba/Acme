package at.bayava.acme.productinventory.db.model

import com.sun.istack.NotNull
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Product(
    @Id
    var name: String,
    @NotNull
    var deliveryDate: LocalDate,
    @NotNull
    var declaredValue: Double
) {
    constructor() : this("", LocalDate.MIN, 0.0) {
    }

}

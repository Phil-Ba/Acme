package at.bayava.acme.categories.db.model

import at.bayava.acme.categories.validation.ValidCategoryLimit
import com.sun.istack.NotNull
import javax.persistence.*
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

@Entity
@ValidCategoryLimit
data class Category(
    @Id
    @SequenceGenerator(name = "CATEGORY_SEQ", sequenceName = "CATEGORY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ")
    var id: Long,
    @NotNull
    var name: String,
    @PositiveOrZero
    var low: Double,
    @NotNull
    var top: Double?,
    @Positive
    var fee: Double
) {
    constructor() : this(-1, "", 0.0, 0.0, 0.0)

}

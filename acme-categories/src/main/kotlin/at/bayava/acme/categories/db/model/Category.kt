package at.bayava.acme.categories.db.model

import com.sun.istack.NotNull
import javax.persistence.*

@Entity
data class Category(
    @Id
    @SequenceGenerator(name = "CATEGORY_SEQ", sequenceName = "CATEGORY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ")
    var id: Long,
    @NotNull
    var name: String,
    @NotNull
    var low: Double,
    @NotNull
    var top: Double
) {
    constructor() : this(-1, "", 0.0, 0.0)

}

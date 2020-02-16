package at.bayava.acme.ui.model.rest

data class ProductType(
    var id: Long,
    var name: String,
    var categoryName: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductType

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

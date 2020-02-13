package at.bayava.acme.productinventory.db.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Product(@Id var name: String)
package at.bayava.acme.productinventory.client.model

import java.io.Serializable

data class Category(val id: Long, val name: String, val low: Double, val top: Double?, val fee: Double) : Serializable
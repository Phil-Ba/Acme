package at.bayava.acme.ui.model.rest

import java.io.Serializable

data class Category(val id: Long, val name: String, val low: Double, val top: Double?, val fee: Double) : Serializable
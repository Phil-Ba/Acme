package at.bayava.acme.ui.view

import at.bayava.acme.ui.model.rest.Category
import at.bayava.acme.ui.model.rest.ProductItem
import at.bayava.acme.ui.model.rest.ProductType
import com.vaadin.flow.component.HasValue.ValueChangeEvent


class ProductItemPresenter(
    private val saveHandler: (ProductItem) -> ProductItem,
    val categoriesByName: Map<String, List<Category>>,
    val productTypes: List<ProductType>
) {
    private val view: ProductItemView

    init {
        view = ProductItemView(categoriesByName, productTypes)

        view.declaredValue.addValueChangeListener(::updateFeeAmount)
        view.productType.addValueChangeListener(::updateFeeAndProductType)
        view.save.addClickListener { save() }
    }

    private fun updateFeeAndProductType(changeEvent: ValueChangeEvent<ProductType>) {
        val value = changeEvent.value
        updateFee(value)
        view.getCurrentItem()?.apply { productType = value }
    }

    private fun updateFeeAmount(event: ValueChangeEvent<Double>) {
        view.feeAmount.value = event.value?.let { it * (view.feePercent.value ?: 0.0) } ?: 0.0
    }

    private fun updateFee(prodType: ProductType?) {
        if (prodType != null) {
            view.productCategory.value = prodType.categoryName
            val feePercentage = findFeeForAmountAndName(prodType.categoryName, view.declaredValue.value)
            view.feePercent.value = feePercentage
            if (view.declaredValue.value != 0.0) {
                view.feeAmount.value = feePercentage * view.declaredValue.value
            } else {
                view.feeAmount.value = 0.0
            }
        } else {
            view.feePercent.value = 0.0
            view.feeAmount.value = 0.0
        }
    }

    fun findFeeForAmountAndName(name: String, amount: Double): Double {
        val categories = categoriesByName[name]
        val category = categories!!.first { category ->
            when {
                category.low <= amount && (category.top?.let { it > amount } ?: true) -> true
                else -> false
            }
        }
        return category.fee
    }

    private fun save() {
        view.getCurrentItem()?.let { saveHandler(it) }
    }

    fun setCurrentItem(item: ProductItem?) {
        view.setCurrentItem(item)
        if (item == null) {
            view.isVisible = false
        } else {
            view.isVisible = true
            updateFee(item.productType)
            view.productType.focus()
        }
    }
}
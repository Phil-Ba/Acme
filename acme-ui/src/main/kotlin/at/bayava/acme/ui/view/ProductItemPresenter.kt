package at.bayava.acme.ui.view

import at.bayava.acme.ui.model.rest.Category
import at.bayava.acme.ui.model.rest.ProductItem
import at.bayava.acme.ui.model.rest.ProductType
import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.HasValue.ValueChangeEvent
import com.vaadin.flow.component.ItemLabelGenerator
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.data.binder.Binder


class ProductItemPresenter(
    private val view: ProductItemView,
    private val categoriesByName: Map<String, List<Category>>,
    private val productTypes: List<ProductType>
) {
    private val binder: Binder<ProductItem>

    init {
        view.setProductTypeItems(productTypes)
        view.setProductTypeItemsLabelGenerator(ItemLabelGenerator<ProductType> { it.name })
        view.listenToDeclaredValueChange(::updateFeeAmount)
        view.listenToProductTypeChange(::updateFeeAndProductType)

        binder = view.initBinder(ProductItem::class.java)

        view.listenToSave(::save)
    }

    private fun updateFeeAndProductType(changeEvent: ValueChangeEvent<ProductType>) {
        val value = changeEvent.value
        updateFee(value)
        binder.bean?.apply { productType = value }
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

    private fun save(event: ClickEvent<Button>) {
        binder.bean.let { TODO() }
    }

    fun setCurrentItem(item: ProductItem?) {
        binder.bean = item
        if (item == null) {
            view.isVisible = false
        } else {
            view.isVisible = true
            updateFee(item.productType)
            view.productType.focus()
        }
    }
}
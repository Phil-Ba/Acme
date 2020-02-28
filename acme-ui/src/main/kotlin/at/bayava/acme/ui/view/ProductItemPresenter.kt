package at.bayava.acme.ui.view

import at.bayava.acme.ui.client.rest.CategoryClient
import at.bayava.acme.ui.client.rest.ProductTypeClient
import at.bayava.acme.ui.model.rest.Category
import at.bayava.acme.ui.model.rest.ProductItem
import at.bayava.acme.ui.model.rest.ProductType
import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.HasValue.ValueChangeEvent
import com.vaadin.flow.component.ItemLabelGenerator
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.data.binder.Binder
import com.vaadin.flow.spring.annotation.UIScope
import org.springframework.stereotype.Component

@UIScope
@Component
class ProductItemPresenter(
    private val categoryClient: CategoryClient,
    private val productTypeClient: ProductTypeClient
) {
    private lateinit var binder: Binder<ProductItem>
    private lateinit var view: ProductItemView
    private lateinit var categoriesByName: Map<String, List<Category>>

    fun bindToView(view: ProductItemView) {
        val productTypes = productTypeClient.fetchProductTypes(1000).content
        categoriesByName = categoryClient.fetchCategories()
            .content
            .groupBy { it.name }
        this.view = view
        view.setProductTypeItems(productTypes.toList())
        view.setProductTypeItemsLabelGenerator(ItemLabelGenerator<ProductType> { it.name })
        view.listenToDeclaredValueChange(::updateFeeAmount)
        view.listenToProductTypeChange(::updateFeeAndProductType)

        binder = view.initBinder(ProductItem::class.java, { t: String -> ProductItem() }, ProductItem::productType.set)
//        binder = view.initBinder(ProductItem::class.java,ProductItem::productType::get,ProductItem::productType.set)

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
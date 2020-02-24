package at.bayava.acme.ui.view

import at.bayava.acme.ui.model.rest.Category
import at.bayava.acme.ui.model.rest.ProductItem
import at.bayava.acme.ui.model.rest.ProductType
import com.vaadin.flow.component.HasValue.ValueChangeEvent
import com.vaadin.flow.component.ItemLabelGenerator
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.combobox.ComboBox
import com.vaadin.flow.component.datepicker.DatePicker
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.NumberField
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.Binder


class ProductItemForm(
    private val saveHandler: (ProductItem) -> ProductItem,
    val categoriesByName: Map<String, List<Category>>,
    val productTypes: List<ProductType>
) : FormLayout(), ItemForm<ProductItem> {
    private val binder = Binder(ProductItem::class.java)
    var deliveryDate = DatePicker("Delivery date")
    var declaredValue = NumberField("Declared value")
    var feePercent = NumberField("Fee")
    var feeAmount = NumberField("Total fee")
    val productType = ComboBox<ProductType>("Product type")
    val productCategory = TextField("Product category")
    val save = Button("Save")
    val delete = Button("Delete")

    init {
        binder.bindInstanceFields(this)
        val buttons = HorizontalLayout(save, delete)
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY)

        productCategory.isReadOnly = true
        deliveryDate.isRequired = true
        productType.isRequired = true
        declaredValue.isRequiredIndicatorVisible = true
        deliveryDate.isRequired = true
        feePercent.isReadOnly = true
        feeAmount.isReadOnly = true

        declaredValue.addValueChangeListener(::updateFeeAmount)

        productType.setItems(productTypes)
        productType.itemLabelGenerator = ItemLabelGenerator { it.name }
        productType.addValueChangeListener(::updateFeeAndProductType)

        save.addClickListener { save() }

        add(productType, productCategory, declaredValue, deliveryDate, feePercent, feeAmount, buttons)
    }

    private fun updateFeeAndProductType(changeEvent: ValueChangeEvent<ProductType>) {
        val value = changeEvent.value
        updateFee(value)
        binder.bean?.apply { productType = value }
    }

    private fun updateFeeAmount(event: ValueChangeEvent<Double>) {
        feeAmount.value = event.value?.let { it * (feePercent.value ?: 0.0) } ?: 0.0
    }

    private fun updateFee(prodType: ProductType?) {
        if (prodType != null) {
            productCategory.value = prodType.categoryName
            val feePercentage = findFeeForAmountAndName(prodType.categoryName, declaredValue.value)
            feePercent.value = feePercentage
            if (declaredValue.value != 0.0) {
                feeAmount.value = feePercentage * declaredValue.value
            } else {
                feeAmount.value = 0.0
            }
        } else {
            feePercent.value = 0.0
            feeAmount.value = 0.0
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
        saveHandler(binder.bean)
    }

    override fun setCurrentItem(item: ProductItem?) {
        binder.bean = item
        if (item == null) {
            isVisible = false
        } else {
            isVisible = true
            updateFee(item.productType)
            productType.focus()
        }
    }
}
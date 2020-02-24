package at.bayava.acme.ui.view

import at.bayava.acme.ui.model.rest.Category
import at.bayava.acme.ui.model.rest.ProductItem
import at.bayava.acme.ui.model.rest.ProductType
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


class ProductItemView(
    val categoriesByName: Map<String, List<Category>>,
    val productTypes: List<ProductType>
) : FormLayout() {
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

        productType.setItems(productTypes)
        productType.itemLabelGenerator = ItemLabelGenerator { it.name }

        add(productType, productCategory, declaredValue, deliveryDate, feePercent, feeAmount, buttons)
    }

    fun setCurrentItem(item: ProductItem?) {
        binder.bean = item
    }

    fun getCurrentItem(): ProductItem? {
        return binder.bean
    }
}
package at.bayava.acme.ui.components

import at.bayava.acme.ui.client.rest.CategoryClient
import at.bayava.acme.ui.client.rest.ProductItemClient
import at.bayava.acme.ui.client.rest.ProductItemsClient
import at.bayava.acme.ui.client.rest.ProductTypeClient
import at.bayava.acme.ui.model.rest.ProductItem
import at.bayava.acme.ui.model.rest.ProductItemPostDto
import at.bayava.acme.ui.model.rest.ProductType
import at.bayava.acme.ui.view.MainView
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
    val mainView: MainView,
    val productItemsClient: ProductItemsClient,
    val productItemClient: ProductItemClient,
    val productTypeClient: ProductTypeClient,
    val categoryClient: CategoryClient
) : FormLayout() {
    private val binder = Binder(ProductItem::class.java)
    var deliveryDate = DatePicker("Delivery date")
    var declaredValue = NumberField("Declared value")
    val productType = ComboBox<ProductType>("Product type")
    val productCategory = TextField("Product category")
    val save: Button = Button("Save")
    val delete: Button = Button("Delete")

    init {
        binder.bindInstanceFields(this)
        //        status.setItems(CustomerStatus.values())
        val buttons = HorizontalLayout(save, delete)
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY)

        val productTypes = productTypeClient.fetchProductTypes(1000).content
        val categories = categoryClient.fetchCategories().content

        productCategory.isReadOnly = true
        deliveryDate.isRequired = true
        productType.isRequired = true
        declaredValue.isRequiredIndicatorVisible = true
        deliveryDate.isRequired = true

        productType.itemLabelGenerator = ItemLabelGenerator { it.name }
        productType.setItems(productTypes)
        productType.addValueChangeListener { changeEvent ->
            val value = changeEvent.value
            if (value != null) {
                productCategory.value = value.categoryName
            }
            binder.bean?.apply { productType = value }
        }
        save.addClickListener { save() }

        add(productType, productCategory, declaredValue, deliveryDate, buttons)
    }

    fun save() {
        val item = binder.bean
        val savedProductItem = productItemClient.saveProductItem(
            ProductItemPostDto(
                item.id,
                item.productType!!.id,
                item.deliveryDate,
                item.declaredValue
            )
        )
        setProductItem(null)
        mainView.updateGrid(savedProductItem)
    }

    fun setProductItem(productItem: ProductItem?) {
        binder.bean = productItem
        if (productItem == null) {
            isVisible = false
        } else {
            isVisible = true
            declaredValue.focus()
        }
    }
}
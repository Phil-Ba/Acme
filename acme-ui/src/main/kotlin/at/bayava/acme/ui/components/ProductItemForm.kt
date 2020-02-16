package at.bayava.acme.ui.components

import at.bayava.acme.ui.model.rest.ProductItem
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.datepicker.DatePicker
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.NumberField
import com.vaadin.flow.data.binder.Binder


class ProductItemForm(mainView: Component) : FormLayout() {
    private val binder = Binder(ProductItem::class.java)
    var deliveryDate = DatePicker("Delivery date")
    var declaredValue = NumberField("Declared value")
    //    private val status: ComboBox<CustomerStatus> = ComboBox<CustomerStatus>("Status")
    val save: Button = Button("Save")
    val delete: Button = Button("Delete")

    init {
        //        status.setItems(CustomerStatus.values())
        val buttons = HorizontalLayout(save, delete)
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        add(declaredValue, deliveryDate, buttons)
        binder.bindInstanceFields(this)
    }

    fun setCustomer(productItem: ProductItem?) {
        binder.bean = productItem
        if (productItem == null) {
            isVisible = false
        } else {
            isVisible = true
            declaredValue.focus()
        }
    }
}
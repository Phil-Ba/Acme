package at.bayava.acme.ui.view

import at.bayava.acme.ui.event.ItemSavedEvent
import at.bayava.acme.ui.model.rest.ProductItem
import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.ComponentEvent
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
import com.vaadin.flow.spring.annotation.UIScope
import org.springframework.stereotype.Component

@UIScope
@Component
class ProductItemView(presenter: ProductItemPresenter) : FormLayout() {
    private lateinit var binder: Binder<*>
    private var deliveryDate = DatePicker("Delivery date")
    var declaredValue = NumberField("Declared value")
    var feePercent = NumberField("Fee")
    var feeAmount = NumberField("Total fee")
    val productType = ComboBox<Any>("Product type")
    val productCategory = TextField("Product category")
    private val save = Button("Save")
    private val delete = Button("Delete")

    init {
        val buttons = HorizontalLayout(save, delete)
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY)

        productCategory.isReadOnly = true
        deliveryDate.isRequired = true
        productType.isRequired = true
        declaredValue.isRequiredIndicatorVisible = true
        deliveryDate.isRequired = true
        feePercent.isReadOnly = true
        feeAmount.isReadOnly = true

        add(productType, productCategory, declaredValue, deliveryDate, feePercent, feeAmount, buttons)
        presenter.bindToView(this)
    }

    //    fun <T,C:com.vaadin.flow.component.Component> addListener(event: Class<T>, listener:ComponentEventListener<ComponentEvent<C>>) {
//        super.addListener(event, listener)
    fun <T : ComponentEvent<*>> addListener(event: Class<T>, listener: (T) -> Unit) {
        super.addListener(event, listener)
    }

    fun fireItemSaved(item: ProductItem) {
        println("Fire event[$item]")
        fireEvent(ItemSavedEvent(this, item))
    }

    fun <T> setProductTypeItems(productTypes: List<T>) {
        (productType as ComboBox<T>).setItems(productTypes)
    }

    fun <T> setProductTypeItemsLabelGenerator(generator: ItemLabelGenerator<T>) {
        (productType as ComboBox<T>).itemLabelGenerator = generator
    }

    fun listenToDeclaredValueChange(listener: (ValueChangeEvent<Double>) -> Unit) {
        declaredValue.addValueChangeListener(listener)
    }

    fun <T> listenToProductTypeChange(listener: (ValueChangeEvent<T>) -> Unit) {
        (productType as ComboBox<T>).addValueChangeListener(listener)
    }

    fun listenToSave(listener: (ClickEvent<Button>) -> Unit) {
        save.addClickListener(listener)
    }

    fun <T, TY> initBinder(itemClass: Class<T>, typeGetter: (T) -> TY, typeSetter: (T, TY) -> Unit): Binder<T> {
        val newBinder = Binder(itemClass)
        binder = newBinder
        newBinder.forField(productType as ComboBox<TY>)
            .bind(typeGetter, typeSetter)
        newBinder.bindInstanceFields(this)
        return newBinder
    }

}
package at.bayava.acme.ui.view

import at.bayava.acme.ui.event.ItemSelectedEvent
import at.bayava.acme.ui.model.rest.ProductItem
import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.ComponentEvent
import com.vaadin.flow.component.ComponentEventBus
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.spring.annotation.UIScope
import org.springframework.stereotype.Component

@UIScope
@Component
class ProductItemListView(val presenter: ProductItemListPresenter) : VerticalLayout() {

    private lateinit var grid: Grid<*>
    private val addProductItemBtn = Button("Add new product item")

    init {
        this.add(addProductItemBtn)
        setSizeFull()
        presenter.bindToView(this)
    }

    fun listenToAddItemClick(listener: (ClickEvent<Button>) -> Unit) {
        addProductItemBtn.addClickListener(listener)
    }

    fun setEventBus(bus: ComponentEventBus) = super.entBus = bus.

    fun fireItemSelected(item: ProductItem) {
        println("Fire event[$item]")
        fireEvent(ItemSelectedEvent(this, item))
    }

    fun <T : ComponentEvent<*>> addListener(event: Class<T>, listener: (T) -> Unit) {
        println("Add listener for prodItem")
        super.addListener(event, listener)
    }


    fun <T> initGrid(c: Class<T>, items: List<T>, vararg columns: String): Grid<T> {
        val newGrid = Grid(c)
        newGrid.setColumns(*columns)
        newGrid.setItems(items)
        grid = newGrid
        this.add(grid)
        return newGrid
    }

}
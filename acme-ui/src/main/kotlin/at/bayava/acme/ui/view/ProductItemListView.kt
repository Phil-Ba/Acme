package at.bayava.acme.ui.view

import com.vaadin.flow.component.ClickEvent
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
        presenter.bindToView(this)
    }

    fun listenToAddItemClick(listener: (ClickEvent<Button>) -> Unit) {
        addProductItemBtn.addClickListener(listener)
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
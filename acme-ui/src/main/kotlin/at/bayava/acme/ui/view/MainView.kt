package at.bayava.acme.ui.view

import at.bayava.acme.ui.model.rest.ProductItem
import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import java.time.LocalDate


class MainView(itemForm: ItemForm<Any>) : VerticalLayout() {

    private val grid = Grid(ProductItem::class.java)

    val items: List<ProductItem>

    init {
        grid.setColumns(
            "productType.name",
            "deliveryDate",
            "declaredValue"
        )
        grid.setItems(items)
        itemForm.setCurrentItem(null)

        grid.asSingleSelect()
            .addValueChangeListener { event: ComponentValueChangeEvent<Grid<ProductItem?>?, ProductItem?>? ->
                itemForm.setProductItem(grid.asSingleSelect().value)
            }
        val addProductItemBtn = Button("Add new product item")
        addProductItemBtn.addClickListener { e ->
            grid.asSingleSelect().clear()
            itemForm.setProductItem(ProductItem(null, null, LocalDate.now(), 0.0))
        }
        val mainContent = HorizontalLayout(grid, form)
        mainContent.setSizeFull()
        add(addProductItemBtn, mainContent)
    }

    fun updateGrid(productItem: ProductItem) {
        if (items.contains(productItem)) {
            items.remove(productItem)
        }
        items.add(productItem)

        grid.dataProvider.refreshAll()
    }
}
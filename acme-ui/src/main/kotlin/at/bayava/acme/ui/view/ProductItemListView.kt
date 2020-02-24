package at.bayava.acme.ui.view

import at.bayava.acme.ui.model.rest.ProductItem
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.VerticalLayout


class ProductItemListView : VerticalLayout() {

    val grid = Grid(ProductItem::class.java)

    init {
        grid.setColumns(
            "productType.name",
            "deliveryDate",
            "declaredValue"
        )
        val addProductItemBtn = Button("Add new product item")
        this.add(addProductItemBtn, grid)
    }

}
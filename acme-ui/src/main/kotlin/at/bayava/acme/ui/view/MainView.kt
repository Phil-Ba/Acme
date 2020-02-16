package at.bayava.acme.ui.view

import at.bayava.acme.ui.client.rest.ProductItemClient
import at.bayava.acme.ui.components.ProductItemForm
import at.bayava.acme.ui.model.rest.ProductItem
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route


@Route("")
class MainView(val productItemClient: ProductItemClient) : VerticalLayout() {

    private val grid = Grid(ProductItem::class.java)
    private val form = ProductItemForm(this)

    init {
        grid.setColumns(
            "productType",
            "deliveryDate",
            "declaredValue"
        )
        add(grid)
        grid.setItems(productItemClient.fetchProductItems().content)

        val mainContent = HorizontalLayout(grid, form)
        mainContent.setSizeFull()
        add(mainContent)
    }
}
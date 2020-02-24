package at.bayava.acme.ui.view

import at.bayava.acme.ui.client.rest.CategoryClient
import at.bayava.acme.ui.client.rest.ProductItemClient
import at.bayava.acme.ui.client.rest.ProductItemsClient
import at.bayava.acme.ui.client.rest.ProductTypeClient
import at.bayava.acme.ui.model.rest.ProductItem
import at.bayava.acme.ui.model.rest.ProductItemPostDto
import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import java.time.LocalDate


@Route("")
class MainPresenter(
    val productItemsClient: ProductItemsClient,
    val productItemClient: ProductItemClient,
    val categoryClient: CategoryClient,
    val productTypeClient: ProductTypeClient
) : VerticalLayout() {

    private val grid = Grid(ProductItem::class.java)
    private val form = ProductItemView(
        this,
        productItemsClient,
        productItemClient,
        productTypeClient,
        categoryClient
    )

    val items: MutableCollection<ProductItem>

    init {
        grid.setColumns(
            "productType.name",
            "deliveryDate",
            "declaredValue"
        )
        items = productItemsClient.fetchProductItems(150).content.toMutableList()
        grid.setItems(items)
        form.setProductItem(null)

        grid.asSingleSelect()
            .addValueChangeListener { event: ComponentValueChangeEvent<Grid<ProductItem?>?, ProductItem?>? ->
                form.setProductItem(grid.asSingleSelect().value)
            }
        val addProductItemBtn = Button("Add new product item")
        addProductItemBtn.addClickListener { e ->
            grid.asSingleSelect().clear()
            form.setProductItem(ProductItem(null, null, LocalDate.now(), 0.0))
        }
        val mainContent = HorizontalLayout(grid, form)
        mainContent.setSizeFull()
        add(addProductItemBtn, mainContent)
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
        setCurrentItem(null)
        mainView.updateGrid(savedProductItem)
    }

    fun updateGrid(productItem: ProductItem) {
        if (items.contains(productItem)) {
            items.remove(productItem)
        }
        items.add(productItem)

        grid.dataProvider.refreshAll()
    }
}
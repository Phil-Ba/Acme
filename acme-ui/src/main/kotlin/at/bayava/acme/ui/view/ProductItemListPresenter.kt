package at.bayava.acme.ui.view

import at.bayava.acme.ui.client.rest.CategoryClient
import at.bayava.acme.ui.client.rest.ProductItemClient
import at.bayava.acme.ui.client.rest.ProductItemsClient
import at.bayava.acme.ui.client.rest.ProductTypeClient
import at.bayava.acme.ui.model.rest.ProductItem
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route


@Route("")
class ProductItemListPresenter(
    val productItemsClient: ProductItemsClient,
    val productItemClient: ProductItemClient,
    val categoryClient: CategoryClient,
    val productTypeClient: ProductTypeClient
) : VerticalLayout() {

    val view = ProductItemListView()
    val items: MutableList<ProductItem>

    init {
        items = productItemsClient.fetchProductItems(150).content.toMutableList()
        view.grid.setItems(items)
//        view.grid.asSingleSelect()
//            .addValueChangeListener { event: ComponentValueChangeEvent<Grid<ProductItem?>?, ProductItem?>? ->
//                form.setProductItem(grid.asSingleSelect().value)
//            }
//        addProductItemBtn.addClickListener { e ->
//            grid.asSingleSelect().clear()
//            form.setProductItem(ProductItem(null, null, LocalDate.now(), 0.0))
//        }
//        val mainContent = HorizontalLayout(grid, form)
//        mainContent.setSizeFull()
//        add(addProductItemBtn, mainContent)
    }

//    fun save() {
//        val item = binder.bean
//        val savedProductItem = productItemClient.saveProductItem(
//            ProductItemPostDto(
//                item.id,
//                item.productType!!.id,
//                item.deliveryDate,
//                item.declaredValue
//            )
//        )
//        setCurrentItem(null)
//        mainView.updateGrid(savedProductItem)
//    }

    fun updateGrid(productItem: ProductItem) {
        if (items.contains(productItem)) {
            items.remove(productItem)
        }
        items.add(productItem)
        view.grid.dataProvider.refreshAll()
    }
}
package at.bayava.acme.ui.view

import at.bayava.acme.ui.client.rest.ProductItemsClient
import at.bayava.acme.ui.event.ItemSavedEvent
import at.bayava.acme.ui.model.rest.ProductItem
import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.spring.annotation.UIScope
import org.springframework.stereotype.Component
import java.time.LocalDate


@UIScope
@Component
class ProductItemListPresenter(
    val productItemsClient: ProductItemsClient
//    val productItemPresenter: ProductItemPresenter
) {
    private lateinit var view: ProductItemListView
    private lateinit var items: MutableList<ProductItem>
    private lateinit var grid: Grid<ProductItem>

    fun bindToView(view: ProductItemListView) {
        this.view = view
        items = productItemsClient.fetchProductItems(150).content.toMutableList()
        grid = view.initGrid(
            ProductItem::class.java, items,
            "productType.name",
            "deliveryDate",
            "declaredValue"
        )

        grid.asSingleSelect()
            .addValueChangeListener { event: ComponentValueChangeEvent<Grid<ProductItem?>?, ProductItem?>? ->
                view.fireItemSelected(grid.asSingleSelect().value)
//                productItemPresenter.setCurrentItem(grid.asSingleSelect().value)
            }

        view.listenToAddItemClick { e ->
            grid.asSingleSelect().clear()
            view.fireItemSelected(ProductItem(null, null, LocalDate.now(), 0.0))
//            productItemPresenter.setCurrentItem(ProductItem(null, null, LocalDate.now(), 0.0))
        }

        view.addListener(ItemSavedEvent::class.java) { event ->
            println("Reveived event[$event]")
            updateGrid(event.productItem)
        }

    }

    private fun updateGrid(productItem: ProductItem) {
        if (items.contains(productItem)) {
            items.remove(productItem)
        }
        items.add(productItem)
        grid.dataProvider.refreshAll()
    }
}
package at.bayava.acme.ui.view

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route("")
class MainView(
    private val itemList: ProductItemListView,
    private val itemView: ProductItemView
) : VerticalLayout() {

    init {
        val addProductItemBtn = Button("Add new product item")
        this.add(addProductItemBtn, HorizontalLayout(itemList, itemView))
        this.setSizeFull()
    }
}
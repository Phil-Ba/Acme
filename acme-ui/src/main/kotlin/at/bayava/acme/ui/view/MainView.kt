package at.bayava.acme.ui.view

import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route("")
class MainView(
    private val itemList: ProductItemListView,
    private val itemView: ProductItemView
) : VerticalLayout() {

    init {
        this.add(HorizontalLayout(itemList, itemView))
        itemList.eventBus = eventBus
        itemView.eventBus = eventBus
        this.setSizeFull()
    }
}
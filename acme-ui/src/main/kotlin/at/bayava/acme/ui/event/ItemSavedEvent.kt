package at.bayava.acme.ui.event

import at.bayava.acme.ui.model.rest.ProductItem
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.ComponentEvent

class ItemSavedEvent(source: Component, val productItem: ProductItem) : ComponentEvent<Component>(source, false)
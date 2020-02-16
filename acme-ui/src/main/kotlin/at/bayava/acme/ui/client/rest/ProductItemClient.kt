package at.bayava.acme.ui.client.rest

import at.bayava.acme.ui.model.rest.ProductItem
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.hateoas.CollectionModel
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("productItemService", url = "\${product-inventory.url}", path = "productItems")
interface ProductItemClient {
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/"]
    )
    fun fetchProductItems(): CollectionModel<ProductItem>
}
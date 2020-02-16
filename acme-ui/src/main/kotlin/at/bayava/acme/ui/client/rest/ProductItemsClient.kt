package at.bayava.acme.ui.client.rest

import at.bayava.acme.ui.model.rest.ProductItem
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.hateoas.CollectionModel
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@FeignClient("productItemsService", url = "\${product-inventory.url}", path = "productItems")
interface ProductItemsClient {
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/"],
        params = ["size"]
    )
    fun fetchProductItems(@RequestParam size: Int): CollectionModel<ProductItem>

}
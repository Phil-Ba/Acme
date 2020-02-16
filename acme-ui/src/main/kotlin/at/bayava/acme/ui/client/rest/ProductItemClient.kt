package at.bayava.acme.ui.client.rest

import at.bayava.acme.ui.model.rest.ProductItem
import at.bayava.acme.ui.model.rest.ProductItemPostDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("productItemService", url = "\${product-inventory.url}", path = "productItem")
interface ProductItemClient {

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/"]
    )
    fun saveProductItem(productItem: ProductItemPostDto): ProductItem

}
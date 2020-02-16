package at.bayava.acme.ui.client.rest

import at.bayava.acme.ui.model.rest.ProductType
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.hateoas.CollectionModel
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@FeignClient("productTypesService", url = "\${product-inventory.url}", path = "productTypes")
interface ProductTypeClient {
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/"],
        params = ["size"]
    )
    fun fetchProductTypes(@RequestParam size: Int): CollectionModel<ProductType>

}
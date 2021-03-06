package at.bayava.acme.productinventory.client.rest

import at.bayava.acme.productinventory.client.model.Category
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.hateoas.CollectionModel
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("categoryService", url = "\${categories.url}", path = "categories")
interface CategoryClient {
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/"]
    )
    fun fetchCategories(): CollectionModel<Category>
}
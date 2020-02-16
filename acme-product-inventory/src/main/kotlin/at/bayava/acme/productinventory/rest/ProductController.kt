package at.bayava.acme.productinventory.rest

import at.bayava.acme.productinventory.db.model.ProductItem
import at.bayava.acme.productinventory.db.model.ProductType
import at.bayava.acme.productinventory.db.repo.ProductItemRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import java.time.LocalDate

@RepositoryRestController
@RequestMapping("/products")
class ProductController {

    @Autowired
    lateinit var productItemRepo: ProductItemRepo

    @ResponseBody
    @RequestMapping("/fooz", method = [RequestMethod.GET])
    fun foobarw(): ProductItem {
        return ProductItem(-1, ProductType(), LocalDate.now(), 23.0)
    }

}
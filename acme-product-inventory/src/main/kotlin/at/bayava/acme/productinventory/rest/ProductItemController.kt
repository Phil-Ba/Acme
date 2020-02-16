package at.bayava.acme.productinventory.rest

import at.bayava.acme.productinventory.db.model.ProductItem
import at.bayava.acme.productinventory.db.repo.ProductItemRepo
import at.bayava.acme.productinventory.db.repo.ProductTypeRepo
import at.bayava.acme.productinventory.rest.model.ProductItemDto
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Validated
@RepositoryRestController
@RequestMapping("/productItem")
class ProductItemController(
    val productItemRepo: ProductItemRepo,
    val productTypeRepo: ProductTypeRepo
) {

    @ResponseBody
    @RequestMapping("", method = [RequestMethod.POST])
    fun insert(@RequestBody dto: ProductItemDto): ProductItem {
        val type = productTypeRepo.findById(dto.productTypeId)
            .orElseThrow { IllegalArgumentException("Unknown type[${dto.productTypeId}]") }

        return productItemRepo.save(ProductItem(-1, type, dto.deliveryDate, dto.declaredValue))
    }

}
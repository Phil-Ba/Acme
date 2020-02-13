package at.bayava.acme.productinventory.main

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = ["at.bayava.acme"])
class ProductInventoryApplication

fun main() {
    SpringApplication.run(ProductInventoryApplication::class.java)
}

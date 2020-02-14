package at.bayava.acme.categories.main

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("at.bayava.acme")
class CategoriesApplication

fun main() {
    SpringApplication.run(CategoriesApplication::class.java)
}

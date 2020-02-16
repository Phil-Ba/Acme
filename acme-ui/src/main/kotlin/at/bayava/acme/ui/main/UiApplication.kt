package at.bayava.acme.ui.main.at.bayava.acme.ui.main

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("at.bayava.acme")
class UiApplication

fun main() {
    SpringApplication.run(UiApplication::class.java)
}

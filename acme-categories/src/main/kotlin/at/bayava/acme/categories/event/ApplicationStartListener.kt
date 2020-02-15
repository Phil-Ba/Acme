package at.bayava.acme.categories.event

import at.bayava.acme.categories.db.model.Category
import at.bayava.acme.categories.db.repo.CategoryRepo
import mu.KotlinLogging
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import java.util.*

private val logger = KotlinLogging.logger {}

@Component
class ApplicationStartListener(val categoryRepo: CategoryRepo) : ApplicationListener<ContextRefreshedEvent> {

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val scanner = Scanner(this::class.java.getResourceAsStream("/categories.csv"))
            .useDelimiter(";|\\r?\\n|\\r")
        scanner.nextLine()

        val categories = mutableListOf<Category>()
        while (scanner.hasNextLine()) {
            val name = scanner.next()
            val low = scanner.next().replace(",", "").toDouble()
            val topString = scanner.next().replace(",", "")
            val top = when (topString) {
                "unlimited" -> null
                else -> topString.toDouble()
            }
            val fee = scanner.next().replace("%", "").toDouble()
            val category = Category(-1, name, low, top, fee)
            logger.info("Created category[$category]")
            categories += category
        }

        categoryRepo.saveAll(categories)
    }

}
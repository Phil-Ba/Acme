
import at.bayava.acme.productinventory.main.ProductInventoryApplication
import io.kotlintest.IsolationMode
import io.kotlintest.extensions.TestListener
import io.kotlintest.specs.ShouldSpec
import io.kotlintest.spring.SpringListener
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = [ProductInventoryApplication::class, SpringTestConfig::class]
)
abstract class SpringBaseSpec : ShouldSpec() {

    override fun isolationMode(): IsolationMode = IsolationMode.InstancePerTest

    override fun listeners(): List<TestListener> = listOf<TestListener>(SpringListener)

}
package at.bayava.acme.test

import io.kotlintest.IsolationMode
import io.kotlintest.extensions.TestListener
import io.kotlintest.specs.AnnotationSpec
import io.kotlintest.spring.SpringListener
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = [SpringBaseSpec::class, SpringTestConfig::class]
)
@ActiveProfiles("test")
class SpringBaseSpec : AnnotationSpec() {

    override fun isolationMode(): IsolationMode = IsolationMode.InstancePerTest

    override fun listeners(): List<TestListener> = listOf<TestListener>(SpringListener)

}
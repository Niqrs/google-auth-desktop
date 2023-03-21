import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
class HelloKotlinFactoryTest {
    @Test
    fun testFactory() {
        assertEquals("Hello Kotlin!", HelloKotlinFactory().generate())
    }
}
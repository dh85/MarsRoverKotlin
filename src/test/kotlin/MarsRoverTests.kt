import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertFailsWith

class MarsRoverTests {

    @Test
    fun `initial position at origin facing north`() {
        assertEquals("0:0:N", makeSUT(""))
    }

    @ParameterizedTest(name = "commands {0} should show cardinal {1}")
    @CsvSource(
        "R, E",
        "RR, S",
        "RRR, W",
        "RRRR, N",
        "RRRRR, E"
    )
    fun `it rotates to the right`(commands: String, cardinal: String) {
        assertEquals("0:0:$cardinal", makeSUT(commands))
    }

    @ParameterizedTest(name = "commands {0} should show cardinal {1}")
    @CsvSource(
        "L, W",
        "LL, S",
        "LLL, E",
        "LLLL, N",
        "LLLLL, W"
    )
    fun `it rotates to the left`(commands: String, cardinal: String) {
        assertEquals("0:0:$cardinal", makeSUT(commands))
    }

    @ParameterizedTest(name = "commands {0} should show position {1}")
    @CsvSource(
        "M, 0:1:N",
        "MM, 0:2:N",
        "MMMMMMMMMMM, 0:0:N",
        "RM, 1:0:E",
        "RMM, 2:0:E",
        "RMMMMMMMMMMM, 0:0:E",
        "LM, 10:0:W",
        "LMM, 9:0:W",
        "LMMMMMMMMMMM, 0:0:W",
        "LLM, 0:10:S",
        "LLMM, 0:9:S",
        "LLMMMMMMMMMMM, 0:0:S"
    )
    fun `it moves forward`(commands: String, expectedPosition: String) {
        assertEquals(expectedPosition, makeSUT(commands))
    }

    @Test
    fun `invalid command passed in throws error`() {
        val error = assertFailsWith(IllegalArgumentException::class) {
            makeSUT("FISH")
        }
        assertEquals("Invalid command passed in", error.message)
    }

    private fun makeSUT(commands: String) = Rover().execute(commands)

}
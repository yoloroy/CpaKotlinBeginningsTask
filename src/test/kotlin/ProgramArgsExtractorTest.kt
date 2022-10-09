import ProgramArgsExtractor.ProgramArgs
import io.ConsoleIO
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.FileNotFoundException

internal class ProgramArgsExtractorTest {

    @Test
    fun `extract ''`() {
        val extractor = ProgramArgsExtractor(emptyArray(), "", "")
        assertEquals(
            ProgramArgs(null, false, false),
            extractor.extract()
        )
    }

    @Test
    fun `extract '-io console'`() {
        val extractor = ProgramArgsExtractor(arrayOf("-io", "console"), "", "")
        assertEquals(
            ProgramArgs(ConsoleIO, false, false),
            extractor.extract()
        )
    }

    @Test
    fun `extract '-io file' causes file_not_found`() {
        val extractor = ProgramArgsExtractor(arrayOf("-io", "file"), "", "")
        assertEquals(
            ProgramArgs(null, false, true),
            extractor.extract()
        )
    }

    @Test
    fun `extract '-help'`() {
        val extractor = ProgramArgsExtractor(arrayOf("-help"), "", "")
        assertEquals(
            ProgramArgs(null, true, false),
            extractor.extract()
        )
    }

    @Test
    fun `extract '-help -io console' or '-io console -help' or '-io -help console'`() {
        run {
            val extractor = ProgramArgsExtractor(arrayOf("-help", "-io", "console"), "", "")
            assertEquals(
                ProgramArgs(ConsoleIO, true, false),
                extractor.extract()
            )
        }
        run {
            val extractor = ProgramArgsExtractor(arrayOf("-io", "console", "-help"), "", "")
            assertEquals(
                ProgramArgs(ConsoleIO, true, false),
                extractor.extract()
            )
        }
        run {
            val extractor = ProgramArgsExtractor(arrayOf("-io", "-help", "console"), "", "")
            assertEquals(
                ProgramArgs(null, true, false),
                extractor.extract()
            )
        }
    }
}

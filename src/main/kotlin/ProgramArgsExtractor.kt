import io.ConsoleIO
import io.FileIO
import io.IO
import java.io.File
import java.io.FileNotFoundException

private const val HELP_ARG = "-help"
private const val IO_ARG = "-io"
private const val IO_CONSOLE_METHOD_ARG = "console"
private const val IO_FILE_METHOD_ARG = "file"

/**
 * Accepted args:
 *  * -io <console | file>
 *  * -help
 */
class ProgramArgsExtractor(
    private val args: Array<String>,
    private val inputFilePath: String,
    private val outputFilePath: String
) {
    private var ioMethod: IO?
    private val isHelpSpecified: Boolean = HELP_ARG in args
    private var isInputFileNotFound: Boolean

    init {
        try {
            ioMethod = extractIoMethod(args, inputFilePath, outputFilePath)
            isInputFileNotFound = false
        } catch (e: FileNotFoundException) {
            ioMethod = null
            isInputFileNotFound = true
        }
    }

    fun extract() = ProgramArgs(ioMethod, isHelpSpecified, isInputFileNotFound)

    companion object {
        fun extractIoMethod(args: Array<String>, inputFilePath: String, outputFilePath: String): IO? {
            val ioArgIndex = args.indexOf(IO_ARG)
            val methodArgIndex = ioArgIndex + 1
            if (ioArgIndex == -1 || args.lastIndex < methodArgIndex) return null

            return when (args[methodArgIndex]) {
                IO_CONSOLE_METHOD_ARG -> ConsoleIO
                IO_FILE_METHOD_ARG -> FileIO(
                    File(inputFilePath).also { if (!it.exists()) throw FileNotFoundException("Input file not found") },
                    File(outputFilePath)
                )
                else -> null
            }
        }
    }

    data class ProgramArgs(
        val ioMethod: IO?,
        val isHelpSpecified: Boolean,
        val isInputFileNotFound: Boolean
    )
}

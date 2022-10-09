import io.ConsoleIO
import io.FileIO
import io.IO

private const val INPUT_FILE_PATH = "input.txt"
private const val OUTPUT_FILE_PATH = "output.txt"
private const val HELP_MESSAGE = "" +
        "Program accepts args:\n" +
        " * -io <console | file>\n" +
        " * -help"

fun main(args: Array<String>) {
    val programArgs = ProgramArgsExtractor(args, INPUT_FILE_PATH, OUTPUT_FILE_PATH).extract()

    programArgs.ioMethod?.let { ioMethod -> task(ioMethod) }
    if (programArgs.ioMethod is FileIO && programArgs.isInputFileNotFound) printInputFileNotFound()
    if (programArgs.isHelpSpecified || programArgs.ioMethod == null) printHelp()
}

fun task(ioMethod: IO) {
    
}

fun printInputFileNotFound() = println("$INPUT_FILE_PATH not found")

fun printHelp() = println(HELP_MESSAGE)

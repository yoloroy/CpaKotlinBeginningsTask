import io.IO
import io.writeln

private const val INPUT_FILE_PATH = "input.txt"
private const val OUTPUT_FILE_PATH = "output.txt"
private const val HELP_MESSAGE = """Program accepts these args:
 * -io <console | file>
 * -help"""
private const val STOP_WORD = "stop"
private const val INPUT_PATTERN = "[0-9]+ *[-+*/] *[0-9]+"
private const val BAD_INPUT = "Bad input, line must match this pattern: r\"$INPUT_PATTERN\"\nExamples: \"1+2\"; \"3* 4\"; \"2 / 2\""
private val radixRange = 2..36

fun main(args: Array<String>) {
    val programArgs = ProgramArgsExtractor(args, INPUT_FILE_PATH, OUTPUT_FILE_PATH).extract()

    programArgs.ioMethod?.let { ioMethod -> task(ioMethod) }
    if (programArgs.isInputFileNotFound) printInputFileNotFound()
    if (programArgs.isHelpSpecified || (programArgs.ioMethod == null && !programArgs.isInputFileNotFound)) printHelp()
}

fun task(ioMethod: IO) {
    fun Int.presentInRadix(radix: Int) = toString(radix)

    val pattern = INPUT_PATTERN.toRegex()

    val radix = radixRange.random()
    ioMethod.writeln("Your random radix for session: $radix")

    while (true) {
        val line = ioMethod.read()

        if (line == STOP_WORD) return
        if (!line.matches(pattern)) {
            ioMethod.writeln(BAD_INPUT)
            continue
        }

        val (a, b, operation) = Expression.parse(line)

        ioMethod.writeln(operation(a, b).presentInRadix(radix))
    }
}

fun printInputFileNotFound() = println("$INPUT_FILE_PATH not found")

fun printHelp() = println(HELP_MESSAGE)

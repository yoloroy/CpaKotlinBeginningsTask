data class Expression(
    val a: Int,
    val b: Int,
    val operator: (Int, Int) -> Int
) {
    companion object {
        private val operatorsBySymbols = mapOf<String, (Int, Int) -> Int>(
            "-" to { a, b -> a - b },
            "+" to { a, b -> a + b },
            "*" to { a, b -> a * b },
            "/" to { a, b -> a / b }
        )

        fun parse(expression: String): Expression {
            val paddedExpression = expression.replace("[-+*/]".toRegex()) { result -> " ${result.value} " }
            val parts = paddedExpression.split(" +".toRegex())
            return Expression(
                parts[0].toInt(),
                parts[2].toInt(),
                operatorsBySymbols[parts[1]] ?: throw IllegalArgumentException("There no such operator")
            )
        }
    }
}

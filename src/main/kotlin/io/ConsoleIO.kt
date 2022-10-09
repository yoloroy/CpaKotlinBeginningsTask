package io

object ConsoleIO : IO {

    override fun read(): String = readln()

    override fun write(string: String) = print(string)
}

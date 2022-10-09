package io

interface IO {

    fun read(): String

    fun write(string: String)
}

fun IO.writeln(string: String) = write("$string\n")

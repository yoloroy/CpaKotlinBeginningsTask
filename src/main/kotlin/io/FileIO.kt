package io

import java.io.File
import java.io.IOException

class FileIO(inputFile: File, private val outputFile: File) : IO {

    private val reader = inputFile.bufferedReader()

    override fun read() = reader.readLine() ?: throw IOException("Line cannot be read")

    override fun write(string: String) = outputFile.appendText(string)
}

package io

import java.io.File
import java.io.IOException

class FileIO(private val inputFile: File, private val outputFile: File) : IO {

    private val reader = inputFile.bufferedReader()
    private val writer = outputFile.printWriter()

    override fun read() = reader.readLine() ?: throw IOException("Line cannot be read")

    override fun write(string: String) = writer.write(string)
}

package io.github.jakejmattson.directoryflattener

import java.io.File

fun main(args: Array<String>) {
    if (args.isNotEmpty())
        processInputFromCLI(args.first())
    else
        processInputFromGUI()
}

fun processInputFromCLI(input: String) {
    val file = File(input)

    val wasSuccessful = file.flatten()
    val response = if (wasSuccessful) "Flattening successful" else "Something went wrong."

    println(response)
}

fun processInputFromGUI(): File {
    throw UnsupportedOperationException("This functionality is not implemented yet! Call this program with arguments.")
}
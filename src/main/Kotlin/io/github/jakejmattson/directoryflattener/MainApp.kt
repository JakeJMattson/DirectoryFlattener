package io.github.jakejmattson.directoryflattener

import tornadofx.*
import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        launch<MyApp>()
    }
    else {
        val inputDirectory = File(args.first())
        val flattenResponse = inputDirectory.flatten()
        println(flattenResponse.message)
    }
}
package io.github.jakejmattson.directoryflattener

import tornadofx.*
import java.io.File

fun main(args: Array<String>) {
    if (args.isNotEmpty())
        processInputFromCLI(args.first())
    else
        processInputFromGUI()
}

fun processInputFromCLI(input: String) {
    val wasSuccessful = File(input).flatten()
    val response = if (wasSuccessful) "Flattening successful" else "Something went wrong."

    println(response)
}

fun processInputFromGUI() {
    launch<MyApp>()
}

class MyApp : App(GUI::class)

class GUI : View() {
    override val root = hbox {
        var file: File? = null

        button {
            text = "Select the directory to flatten."

            setOnAction {
                file = chooseDirectory {
                    title = "Select the directory to flatten."
                }
            }
        }

        button {
            text = "Submit"
            setOnAction {
                val wasFlattened = file?.flatten() ?: false

                if (wasFlattened)
                    println("File flattened")
            }
        }
    }
}
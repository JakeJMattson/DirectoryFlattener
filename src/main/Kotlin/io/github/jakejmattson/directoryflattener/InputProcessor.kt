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
    val flattenResponse = File(input).flatten()

    println(flattenResponse.message)
}

fun processInputFromGUI() {
    launch<MyApp>()
}

class MyApp : App(GUI::class)

class GUI : View("Directory Flattener") {
    override val root = hbox {
        val textInput = textfield {
            prefWidth = 300.0
            promptText = "Directory to flatten"
        }

        button {
            text = "Select"

            setOnAction {
                textInput.text = chooseDirectory {
                    title = "Select the directory to flatten."
                }?.absolutePath
            }
        }

        button {
            text = "Flatten"

            setOnAction {
                val file = File(textInput.text)
                val flattenResponse = file.flatten()

                println(flattenResponse.message)
            }
        }
    }
}
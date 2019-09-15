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
                val flattenResponse = file?.flatten()

                println(flattenResponse?.message)
            }
        }
    }
}
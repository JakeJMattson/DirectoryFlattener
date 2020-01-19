package me.jakejmattson.directoryflattener

import javafx.scene.control.Alert.AlertType
import tornadofx.*
import java.io.File

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
                }?.absolutePath ?: ""
            }
        }

        button {
            text = "Flatten"

            setOnAction {
                val file = File(textInput.text)
                val flattenResponse = file.flatten()
                val alertType = if (flattenResponse.wasSuccessful) AlertType.CONFIRMATION else AlertType.ERROR

                alert(alertType, "", flattenResponse.message)
            }
        }
    }
}
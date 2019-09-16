package io.github.jakejmattson.directoryflattener

import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

data class FlattenResponse(val wasSuccessful: Boolean, val message: String)

private infix fun Boolean.withMessage(message: String) = FlattenResponse(this, message)

fun File.flatten(): FlattenResponse {
    if (!exists())
        return false withMessage "A directory with this path does not exist."

    if (!isDirectory)
        return false withMessage "Input path should be a directory, not a file."

    walkDirectories().forEach {
        if (it == this)
            return@forEach

        val targetFile = File("$path/${it.name}")

        if (!targetFile.exists())
            Files.move(it.toPath(), targetFile.toPath(), StandardCopyOption.ATOMIC_MOVE)
    }

    return true withMessage "Flatten Successful"
}

private fun File.walkDirectories() = walkBottomUp()
    .onLeave {
        if (it.exists() && it.isDirectory)
            if (it.listFiles().isEmpty())
                it.delete()
    }
    .onFail { file, ioException ->
        println("${ioException.message} - ${file.absolutePath}")
    }
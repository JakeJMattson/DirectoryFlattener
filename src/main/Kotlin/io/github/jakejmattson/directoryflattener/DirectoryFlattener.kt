package io.github.jakejmattson.directoryflattener

import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

fun File.flatten(): Boolean {
    validate()

    walkDirectories().forEach {
        if (it == this)
            return@forEach

        val targetFile = File("$path/${it.name}")

        if (!targetFile.exists())
            Files.move(it.toPath(), targetFile.toPath(), StandardCopyOption.ATOMIC_MOVE)
    }

    return true
}

private fun File.validate() {
    require(exists()) { "A directory with this path does not exist." }
    require(isDirectory) { "Input path should be a directory, not a file." }
}

private fun File.walkDirectories() = walkBottomUp()
    .onLeave {
        if (it.exists())
            if (it.isDirectory)
                if (it.listFiles().isEmpty())
                    it.delete()
    }
    .onFail { file, ioException ->
        println("${ioException.message} - ${file.absolutePath}")
    }
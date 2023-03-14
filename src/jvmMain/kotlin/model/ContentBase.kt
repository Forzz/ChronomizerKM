package model

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

abstract class ContentBase(private var file: File, private val isDeleteCopies: Boolean) {

    var name = file.path.substringAfterLast('\\')
    private val type = file.path.substringAfterLast('.')

    private val inputFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
    private val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")

    private fun convertDate(): String {
        val date: String = Date(file.lastModified()).toString()
        val inputDateTime = LocalDateTime.parse(date, inputFormatter)

        return inputDateTime.format(outputFormatter)
    }

    fun convertName() {
        val directory = file.parent
        val newPath = directory + "/" + convertDate() + "." + type
        val renamed = file.renameTo(File(newPath))

        if (!renamed) {
            if (isDeleteCopies) {
                file.delete()
                println("The file ${this.name} is deleted")
            }
        } else {
            val temp = name
            file = File(newPath)
            name = file.path.substringAfterLast('\\')
            println("File $temp converted into $name")
        }
    }
}
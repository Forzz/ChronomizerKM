import model.Photo
import model.Video
import java.io.File

class Converter(private val path: String, private val isDeleteCopies: Boolean) {

    private val directory = File(path)

    fun convertContent() {
        if (directory.isDirectory) {
            val files = directory.listFiles()
            if (files != null) {
                for (file in files) {
                    if (file.isFile) {
                        when (file.getFileType()) {
                            in "photo" -> {
                                val photo = Photo(file, isDeleteCopies)
                                photo.convertName()
                            }
                            in "video" -> {
                                val video = Video(file, isDeleteCopies)
                                video.convertName()
                            }
                            else -> {
                                println("Unknown file")
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun File.getFileType(): String {
    val pictureExtensions: Array<String> = arrayOf("jpeg", "jpg", "png")
    val videoExtensions: Array<String> = arrayOf("mp4", "avi", "wmv", "mov")

    return when (this.extension.lowercase()) {
        in pictureExtensions -> {
            "photo"
        }
        in videoExtensions -> {
            "video"
        }
        else -> {
            "unknown"
        }
    }
}
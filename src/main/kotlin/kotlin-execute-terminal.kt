import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.openrndr.application
import org.openrndr.ffmpeg.VideoPlayerFFMPEG
import java.io.File

fun main() = application {

    // -- get JSON File and transform it into a List
    val gson = Gson()
    val jsonString = File("data/json/listOfVideos.json").readText()
    val typeToken = object : TypeToken<List<Videos>>() {}
    val listOfVideos = gson.fromJson<List<Videos>>(jsonString, typeToken.type)

    program {

        val videos = mutableListOf<VideoPlayerFFMPEG>()
        listOfVideos.forEachIndexed { index, i ->
            println(i.videoPath)

            var a = Runtime.getRuntime().exec("/usr/local/bin/ffmpeg -i ${i.videoPath} -ss 00:00:00.00 -vframes 1 /Users/ferdinandsorg/Downloads/thumbnails/${index}.jpg")

            }

        extend {
        }

    }
}

data class Videos(
    val videoPath: String
)
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.isolated
import org.openrndr.draw.loadFont
import org.openrndr.math.map
import java.io.File

fun main() = application {
    configure {
        width = 1280
        height = 720
    }

    // -- get JSON File and transform it into a List
    val gson = Gson()
    val jsonString = File("data/json/data.json").readText()
    val typeToken = object : TypeToken<List<Frame>>() {}
    val listOfRectangles = gson.fromJson<List<Frame>>(jsonString, typeToken.type)

    program {
        // -- load Font
        val font = loadFont("data/fonts/IBMPlexMono-Regular.ttf", 18.0)

        val firstFrame = listOfRectangles[0].tic
        val lastFrame = listOfRectangles[listOfRectangles.size - 1].tic

        // -- Loop
        var count = firstFrame

        extend {

            // -- loop the JSON File
            if(count == lastFrame) {
                count = firstFrame
            }

            listOfRectangles.filter { it.tic == count }.forEachIndexed { index, frame ->
                drawer.stroke = ColorRGBa.RED
                drawer.fill = null

                // -- draw Rectangles of JSON File
                drawer.rectangle(frame.x, frame.y, frame.width, frame.height)

                // -- coordinates of Rectangle
                drawer.isolated {
                    drawer.fontMap = font
                    drawer.fill = ColorRGBa.RED
                    drawer.text("x${frame.x} y${frame.y} w${frame.width} h${frame.height}", frame.x + 10.0, frame.y + 20.0)
                }

                // -- center of Rectangle
                drawer.isolated {
                    drawer.fill = null
                    drawer.stroke = ColorRGBa.YELLOW
                    drawer.circle(frame.x + (frame.width/2), frame.y + (frame.height/2), 10.0)
                }
            }

            count++
        }
    }
}

data class Frame(
    val height: Double,
    val tic: Int,
    val width: Double,
    val x: Double,
    val y: Double
)
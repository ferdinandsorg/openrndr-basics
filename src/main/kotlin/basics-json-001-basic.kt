import com.google.gson.Gson
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.loadFont
import java.io.File

fun main() = application {
    configure {
        width = 1280
        height = 720
        windowResizable = true
    }
    
    // -- get JSON File
    val gson = Gson()
    val jsonString = File("data/colors.json").readText()
    val listOfColors = gson.fromJson(jsonString, ColorCode::class.java)


    program {
        // -- load Font
        val font = loadFont("data/fonts/IBMPlexMono-Regular.ttf", 22.0)

        extend {
            drawer.background(ColorRGBa.GRAY)
            drawer.stroke = null
            drawer.fontMap = font

            listOfColors.colors.forEachIndexed { index, i ->

                val lineHeight = 26.0
                val row = 26
                val column = index / row

                drawer.fill = ColorRGBa.Companion.fromHex(i.colorCode)
                drawer.text(i.colorName, lineHeight + column * 220.0, lineHeight + index%row * lineHeight)

            }

        }
    }
}

data class ColorCode(
    val colors: List<Colors>
)

data class Colors(
    val colorName: String,
    val colorCode: String
)
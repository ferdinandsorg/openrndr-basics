import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.math.map
import org.openrndr.math.transforms.transform
import org.openrndr.shape.Composition
import org.openrndr.svg.loadSVG
import java.io.File
import kotlin.math.sin

fun main() = application {

    val setWidth = 1280
    val setHeight = 720

    configure {
        width = setWidth
        height = setHeight
    }

    program {

//        extend(ScreenRecorder())

        val smileyComposition: Composition = loadSVG(File("data/svg/smiley.svg").readText())

        extend {
            drawer.background(ColorRGBa.GRAY)

            drawer.fill = ColorRGBa.YELLOW
            drawer.stroke = ColorRGBa.BLACK

            drawer.composition(smileyComposition)

        }

    }
}

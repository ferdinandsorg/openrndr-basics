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

        // -- load 100 SVG smileys in var smileys
        val smileys = mutableListOf<Composition>()
        for (i in 0 .. 99) {
            smileys.add(loadSVG(File("data/svg/smileys/smiley_${i}.svg").readText()))
        }

        extend {
            drawer.background(ColorRGBa.GRAY)

            drawer.fill = ColorRGBa.YELLOW
            drawer.stroke = ColorRGBa.BLACK

            // -- select smileys through X axis mouse movement
            var mouseX = 0
            if (!(mouse.position.x <= 0 || mouse.position.x >= width)) {
                mouseX = map(0.0, width.toDouble(), 0.0, smileys.size.toDouble(), mouse.position.x).toInt()
            }

            // -- sinWave for animation
            val sinWave = map(-1.0, 1.0, 40.0, 60.0, sin(seconds))

            val smileyComponents = smileys[mouseX].findShapes()

            smileyComponents.forEachIndexed { index, i ->

                val m = transform {

                    // -- set position in center
                    translate(width/2.0 - (smileyComponents[0].bounds.center.x), height/2.0 - (smileyComponents[0].bounds.center.x))
                }

                val smileyShape = i.shape.transform(m)

                val smileyContours = smileyShape.contours[0].equidistantPositions((smileyComponents[index].bounds.contour.length / sinWave).toInt())
                drawer.circles(smileyContours, smileyComponents[index].bounds.contour.length / sinWave)

            }

        }
    }
}

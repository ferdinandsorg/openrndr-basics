import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.ffmpeg.ScreenRecorder
import org.openrndr.math.Vector2
import org.openrndr.math.map
import org.openrndr.math.transforms.transform
import org.openrndr.shape.*
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

        val smileys = mutableListOf<Composition>()

        // -- load 100 SVG smileys in var smileys
        for (i in 0 .. 99) {
            smileys.add(loadSVG(File("data/svg/smileys/smiley_${i}.svg").readText()))
        }

        extend {
            drawer.background(ColorRGBa.BLACK)

            drawer.stroke = ColorRGBa.WHITE

            // -- select smileys through X axis mouse movement
            var mouseX = 0
            var mouseY = 0

            if (!(mouse.position.x <= 0 || mouse.position.x >= width)) {
                mouseX = map(0.0, width.toDouble(), 0.0, smileys.size.toDouble(), mouse.position.x).toInt()
            }

            if (!(mouse.position.y <= 0 || mouse.position.y >= height)) {
                mouseY = map(0.0, height.toDouble(), 0.0, 10.0, mouse.position.y).toInt()
            }

            // -- select 1 of 100 smileys by using x mouse position
            val selectSmiley = smileys[mouseX].findShapes()

            selectSmiley.forEachIndexed { index, smiley ->

                // -- get all segments of the SVG
                val smileySegments = smiley.shape.outline.segments

                smileySegments.forEachIndexed { index, segment ->

                    val position = transform {
                        // -- set position in center
                        translate(width/2.0 - (selectSmiley[0].bounds.center.x), height/2.0 - (selectSmiley[0].bounds.center.x))

                        // -- generate wiggle effect
                        translate(Vector2(10.0 * (1 - Math.random()) * mouseY))
                    }

                    // -- get all elements of each segment
                    val b = ShapeContour(listOf(segment), false)

                    // -- draw segment
                    drawer.contour(b.transform(position))

                }
            }

        }
    }
}
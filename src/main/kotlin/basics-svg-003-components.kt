import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.math.map
import org.openrndr.math.transforms.transform
import org.openrndr.shape.Composition
import org.openrndr.svg.loadSVG
import java.io.File

fun main() = application {

    val setWidth = 1280
    val setHeight = 720

    configure {
        width = setWidth
        height = setHeight
    }

    program {

        val smileyComposition: Composition = loadSVG(File("data/svg/smiley.svg").readText())

        extend {
            drawer.background(ColorRGBa.GRAY)

            drawer.fill = ColorRGBa.YELLOW
            drawer.stroke = ColorRGBa.BLACK

            val smileyComponents = smileyComposition.findShapes()

            smileyComponents.forEachIndexed { index, i ->

                // -- SVG scale factor
                val scaleFactor = 1.0

                val mouseX = map(0.0, width.toDouble(), 0.0, smileyComponents.size.toDouble(), mouse.position.x).toInt()

                val m = transform {

                    // -- set position in center
                    translate(width/2.0 - (smileyComposition.root.bounds.center.x * scaleFactor), height/2.0 - (smileyComposition.root.bounds.center.y * scaleFactor))

//                    rotate(Vector3.UNIT_Z, 360 * seconds)
//                    translate( 100.0 * sin(Math.PI * seconds + index), 0.0, 0.0)

                    // -- scale SVG
                    scale(scaleFactor, scaleFactor, scaleFactor)
                }

                if(index == mouseX) {
                    drawer.shape(i.shape.transform(m))
                }
            }

        }

    }
}

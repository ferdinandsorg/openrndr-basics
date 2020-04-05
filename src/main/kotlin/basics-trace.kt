import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.isolatedWithTarget
import org.openrndr.draw.renderTarget
import org.openrndr.draw.tint
import org.openrndr.math.Vector2
import org.openrndr.shape.contour

fun main() = application {

    val setWidth = 500
    val setHeight = 500

    configure {
        width = setWidth
        height = setHeight
    }

    program {

        val rt = renderTarget(width, height) {
            colorBuffer()
        }

        drawer.isolatedWithTarget(rt) {
            drawer.ortho(rt)
            drawer.background(ColorRGBa.BLACK)
        }


        extend {
            drawer.stroke = null

            drawer.isolatedWithTarget(rt) {
                drawer.ortho(rt)

                drawer.fill = ColorRGBa.BLACK.opacify(0.05)
                drawer.rectangle(0.0, 0.0, width.toDouble(), height.toDouble())

                drawer.fill = ColorRGBa.WHITE
                drawer.circle(mouse.position, 25.0)
            }
            drawer.image(rt.colorBuffer(0))

        }
    }
}
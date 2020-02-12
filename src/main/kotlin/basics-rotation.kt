import org.openrndr.application
import org.openrndr.color.ColorRGBa

fun main(args: Array<String>) {
    application {
        configure {
            width = 770
            height = 578
        }
        program {

            extend {
                drawer.fill = ColorRGBa.PINK
                drawer.stroke = null

                val w = 100.0
                val h = 100.0

                // -- translate to the center
                drawer.translate(width/2.0, height/2.0)

                // -- rotate
                drawer.rotate(seconds * 200.0)

                // -- rectangle around the origin
                drawer.rectangle(-(w/2), -(h/2), w, h)
            }
        }
    }
}
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extensions.Screenshots

fun main() = application {
    configure {
        width = 770
        height = 578
    }

    program {

        // Screenshot simple
//            extend(Screenshots())

        // Screenshot expand
        extend(Screenshots()) {
            scale = 2.0
        }

        // -> press SPACE

        extend {

            drawer.fill = ColorRGBa.PINK
            drawer.circle(mouse.position, 100.0)

        }
    }
}
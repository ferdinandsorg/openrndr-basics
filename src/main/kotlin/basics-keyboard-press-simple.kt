import org.openrndr.*
import org.openrndr.color.ColorRGBa
import org.openrndr.math.Vector2


fun main() = application {
    configure {
        width = 640
        height = 480
    }
    program {

        var makeVisible = false

        keyboard.keyUp.listen {

            if (it.name == "c") {
                makeVisible = !(it.name == "c" && makeVisible)
            }

        }

        extend {
            drawer.background(ColorRGBa.BLACK)
            drawer.fill = ColorRGBa.PINK

            if (makeVisible) {
                drawer.circle(Vector2(width/2.0,height/2.0), 50.0)
            }

        }
    }
}
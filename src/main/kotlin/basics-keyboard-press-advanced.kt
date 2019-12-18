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
        var circleRadius = 50.0
        var circleX = width/2.0
        val circleY = height/2.0

        keyboard.keyUp.listen {

            if (it.name == "c") {
                makeVisible = !(it.name == "c" && makeVisible)
            }

        }


        // -- press again
        keyboard.keyDown.listen {

            if (it.key == KEY_ARROW_UP) {
                circleRadius++
            }

            if (it.key == KEY_ARROW_DOWN) {
                circleRadius--
            }

        }

        // -- press and hold
        keyboard.keyRepeat.listen {

            if (it.key == KEY_ARROW_RIGHT) {
                circleX++
            }

            if (it.key == KEY_ARROW_LEFT) {
                circleX--
            }

        }

        extend {
            drawer.background(ColorRGBa.BLACK)
            drawer.fill = ColorRGBa.PINK

            if (makeVisible) {
                drawer.circle(Vector2(circleX,circleY), circleRadius)
            }

        }
    }
}
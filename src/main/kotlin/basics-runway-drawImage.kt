import org.openrndr.*
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extra.runway.*
import org.openrndr.ffmpeg.ScreenRecorder
import org.openrndr.ffmpeg.VideoPlayerFFMPEG
import org.openrndr.math.Vector2

fun main() = application {
    configure {
        width = 900*2
        height = 600
    }

    program {

        extend(ScreenRecorder())

        val rt = renderTarget(900, 600) {
            colorBuffer()
        }

        drawer.isolatedWithTarget(rt) {
            drawer.background(ColorRGBa.TRANSPARENT)
        }



        var isClicked = false

        var color = ColorRGBa.BLACK
        var brushSize = 20.0
        var shape = "rect"
        var submit = false

        // choose color
        keyboard.keyUp.listen {
            if (it.name == "r") {
                color = ColorRGBa.RED
            }

            if (it.name == "g") {
                color = ColorRGBa.GREEN
            }

            if (it.name == "b") {
                color = ColorRGBa.BLUE
            }

            if (it.name == "w") {
                color = ColorRGBa.WHITE
            }

            if (it.name == "p") {
                color = ColorRGBa.PINK
            }

            if (it.name == "y") {
                color = ColorRGBa.YELLOW
            }

            if (it.name == "m") {
                shape = "rect"
            }

            if (it.name == "c") {
                shape = "circ"
            }

            if (it.key == KEY_ENTER) {
                submit = true
            }
        }

        // -- change Radius of Brush
        keyboard.keyRepeat.listen {

            if (it.key == KEY_ARROW_UP) {
                brushSize++
            }

            if (it.key == KEY_ARROW_DOWN) {
                brushSize--
            }

        }

        mouse.clicked.listen {
            isClicked = !(isClicked)
        }

        extend {
            drawer.background(ColorRGBa.WHITE)

            drawer.stroke = null

            drawer.isolatedWithTarget(rt) {

                drawer.ortho(rt)

                drawer.fill = color

                if (isClicked) {

                    if (shape == "rect") {
                        drawer.rectangle(mouse.position.x - brushSize, mouse.position.y - brushSize, brushSize*2.0, brushSize*2.0)
                    }
                    if (shape == "circ") {
                        drawer.circle(mouse.position, brushSize)
                    }

                }

            }

            // draw canvas
            drawer.image(rt.colorBuffer(0))

            // cursor
            drawer.isolated {
                drawer.fill = color.opacify(0.3)
                drawer.stroke = ColorRGBa.GRAY.opacify(0.5)
                if (shape == "rect") {
                    drawer.rectangle(mouse.position.x - brushSize, mouse.position.y - brushSize, brushSize*2.0, brushSize*2.0)
                }
                if (shape == "circ") {
                    drawer.circle(mouse.position, brushSize)
                }
            }


            if (submit) {

                val result: SpadeCocoResult = runwayQuery("http://localhost:8000/query", SpadeCocoRequest(rt.colorBuffer(0).toData()))
                val image = ColorBuffer.fromData(result.output)

                drawer.image(image, 900.0 ,0.0)
                image.destroy()

            }

        }
    }
}
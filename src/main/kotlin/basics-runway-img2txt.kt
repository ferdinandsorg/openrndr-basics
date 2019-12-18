import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extra.runway.*
import org.openrndr.ffmpeg.ScreenRecorder
import org.openrndr.math.smoothstep
import org.openrndr.shape.Rectangle
import org.openrndr.text.Writer

fun main() = application {
    configure {
        width = 480*2
        height = 320
    }

    program {
        //        extend(ScreenRecorder())

        val rt = renderTarget(480, 320) {
            colorBuffer()
        }
        val font = loadFont("data/fonts/IBMPlexMono-Regular.ttf", 24.0)

        val img = loadImage("data/images/example-img/img_0.jpg")

        extend {

            drawer.isolatedWithTarget(rt) {
                drawer.ortho(rt)
                drawer.image(img, 0.0, 0.0)
            }

            // source Image
            drawer.image(rt.colorBuffer(0), 0.0, 0.0)

            val result: im2txtResult = runwayQuery("http://localhost:8000/query", im2txtRequest(rt.colorBuffer(0).toData()))

            val text = result.caption

            drawer.fill = ColorRGBa.WHITE
            drawer.fontMap = font

            val writer = Writer(drawer)
            writer.apply {
                writer.box = Rectangle(500.0, 40.0, 440.0, 240.0)
                text(text)
            }
        }
    }
}
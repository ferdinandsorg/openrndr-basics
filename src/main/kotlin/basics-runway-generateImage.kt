import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extra.runway.*
import org.openrndr.ffmpeg.ScreenRecorder
import org.openrndr.math.smoothstep
import org.openrndr.shape.Composition
import org.openrndr.shape.Rectangle
import org.openrndr.svg.loadSVG
import org.openrndr.text.Writer
import java.awt.Image
import java.io.File

fun main() = application {
    configure {
        width = 256*2
        height = 256
    }

    program {

//        extend(ScreenRecorder())

        val font = loadFont("data/fonts/IBMPlexMono-Regular.ttf", 18.0)

        val inputText = "A yellow car in the nature."

        extend {

            drawer.fill = ColorRGBa.WHITE
            drawer.fontMap = font

            val imgd: CaptionResult = runwayQuery("http://localhost:8000/query", CaptionRequest(inputText))

            val outputImage = ColorBuffer.fromData(imgd.result)

            val writer = Writer(drawer)
            writer.apply {
                writer.box = Rectangle(20.0, 40.0, 216.0, 176.0)
                text(inputText)
            }

            drawer.image(outputImage, 256.0, 0.0, 256.0, 256.0)

        }
    }
}
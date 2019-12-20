import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.isolatedWithTarget
import org.openrndr.draw.loadFont
import org.openrndr.draw.loadImage
import org.openrndr.draw.renderTarget
import org.openrndr.extra.runway.*
import org.openrndr.ffmpeg.ScreenRecorder
import org.openrndr.shape.Rectangle
import org.openrndr.text.Writer

fun main() = application {
    configure {
        width = 480*2
        height = 320
    }

    program {

//        extend(ScreenRecorder())

        val font = loadFont("data/fonts/IBMPlexMono-Regular.ttf", 24.0)

        val inputText = "Lovely Diary, today I "

        extend {

            val result: gpt2Result = runwayQuery("http://localhost:8000/query", gpt2Request(inputText))

            val text = result.text

            drawer.fill = ColorRGBa.WHITE
            drawer.fontMap = font

            val inputTextBox = Writer(drawer)
            inputTextBox.apply {
                inputTextBox.box = Rectangle(20.0, 40.0, 440.0, 240.0)
                text("$inputText...")
            }

            val outputTextBox = Writer(drawer)
            outputTextBox.apply {
                outputTextBox.box = Rectangle(500.0, 40.0, 440.0, 240.0)
                text(text)
            }
        }
    }
}
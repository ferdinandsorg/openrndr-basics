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
        height = 640
    }

    program {

//        extend(ScreenRecorder())

        val font = loadFont("data/fonts/IBMPlexMono-Regular.ttf", 18.0)

        val inputImage = loadImage("data/images/image-example-people-900x600.jpg")
        val inputCategory = "person"

        val rt = renderTarget(900, 600) {
            colorBuffer()
            drawer.background(ColorRGBa.WHITE)
        }

        extend {
            drawer.background(ColorRGBa.BLACK)
            drawer.stroke = null

            drawer.isolatedWithTarget(rt) {
                drawer.ortho(rt)
                drawer.image(inputImage)
            }

            // draw Input Image
            drawer.image(rt.colorBuffer(0))

            drawer.fontMap = font
            drawer.fill = ColorRGBa.WHITE
            drawer.text(inputCategory, 20.0, 620.0)

            // result Image
            val result: MaskRCNNResult = runwayQuery("http://localhost:8000/query", MaskRCNNRequest(inputImage.toDataUrl(), inputCategory))

//            MaskRCNNRequest(rt.colorBuffer(0).toData(), inputCategory)

            val image = ColorBuffer.fromData(result.output)

            drawer.image(image, 900.0 ,0.0)
            image.destroy()
        }
    }
}
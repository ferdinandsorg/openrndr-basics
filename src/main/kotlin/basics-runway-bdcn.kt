import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extra.runway.*
import org.openrndr.ffmpeg.ScreenRecorder
import org.openrndr.ffmpeg.VideoPlayerFFMPEG
import org.openrndr.math.smoothstep

fun main() = application {
    configure {
        width = 900*2
        height = 600
    }

    program {

        val rt = renderTarget(900, 600) {
            colorBuffer()
        }
        val font = loadFont("data/fonts/IBMPlexMono-Regular.ttf", 256.0)

        val loadIMG = loadImage("/Users/ferdinandsorg/Documents/GitHub/solid-geographies/data/images/foreground.jpg")

        val forgroundVideo = VideoPlayerFFMPEG.fromFile("/Users/ferdinandsorg/Documents/GitHub/solid-geographies/data/vid/softbox_v2.mp4")
        forgroundVideo.play()




//        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
//        extend(ScreenRecorder())
        extend {

            drawer.isolatedWithTarget(rt) {
                drawer.background(ColorRGBa.BLACK)
                drawer.ortho(rt)
//                drawer.image(loadIMG, 0.0, 0.0)
                forgroundVideo.draw(drawer)
            }
            val result: BdcnResult =
                runwayQuery("http://localhost:8000/query", BdcnRequest(rt.colorBuffer(0).toData()))

            val image = ColorBuffer.fromData(result.output_image)
            drawer.image(rt.colorBuffer(0))
            drawer.image(image, 900.0 ,0.0)
            image.destroy()
        }
    }
}
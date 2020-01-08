import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.ColorBuffer
import org.openrndr.draw.isolatedWithTarget
import org.openrndr.draw.loadImage
import org.openrndr.draw.renderTarget
import org.openrndr.extra.runway.*
import org.openrndr.ffmpeg.ScreenRecorder
import org.openrndr.ffmpeg.VideoPlayerFFMPEG

fun main() = application {
    configure {
        width = 900*2
        height = 600
    }

    program {

//        extend(ScreenRecorder())

//        val rt = renderTarget(900, 600) {
//            colorBuffer()
//        }
//
//        val imageInput = loadImage("data/images/image-example-people-900x600.jpg")
//
//        val videoInput = VideoPlayerFFMPEG.fromFile("data/videos/video-example-900x600.mp4")
//        videoInput.play()
//
//        extend {
//
//            drawer.isolatedWithTarget(rt) {
//                drawer.background(ColorRGBa.BLACK)
//                drawer.ortho(rt)
//                drawer.image(imageInput, 0.0, 0.0)
//
//                videoInput.draw(drawer)
//            }
//
//            val result: hsResult =
//                runwayQuery("http://localhost:8000/query", hsRequest(rt.colorBuffer(0).toData()))
//
//            val image = ColorBuffer.fromData(result.output_image)
//            drawer.image(rt.colorBuffer(0))
//            drawer.image(image, 900.0 ,0.0)
//            image.destroy()
//
//        }
    }
}
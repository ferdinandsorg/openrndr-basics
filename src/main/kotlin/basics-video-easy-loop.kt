import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.ffmpeg.FFMPEGVideoPlayer
import org.openrndr.ffmpeg.VideoPlayerFFMPEG

fun main() = application {
    // 16:9
    val setWidth = 1280
    val setHeight = 720

    configure {
        width = setWidth
        height = setHeight
    }

    program {

        val video1 = VideoPlayerFFMPEG.fromFile("data/videos/video-example.mp4")
        video1.play()

        val videoRT1 = renderTarget(1280, 720) {
            colorBuffer()
        }

        video1.ended.listen {
            video1.restart()
        }

        extend {
            drawer.withTarget(videoRT1) {
                ortho(videoRT1)
                video1.draw(drawer)
            }

            drawer.image(videoRT1.colorBuffer(0))
        }
    }
}
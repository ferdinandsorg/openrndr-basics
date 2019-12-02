import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import videoControl.FFMPEGVideoPlayerLocal

// Make a new Package of FFMPEGVideoPlayer -> FFMPEGVideoPlayerLocal

fun main() = application {
    // 16:9
    val setWidth = 1280
    val setHeight = 720

    configure {
        width = setWidth
        height = setHeight
    }

    program {
        val video1 = FFMPEGVideoPlayerLocal.fromFile("data/videos/video-example.mp4")
        video1.start()

        val videoRT1 = renderTarget(1280, 720) {
            colorBuffer()
        }

        extend {
            drawer.withTarget(videoRT1) {
                video1.next()
                video1.draw(drawer)

                // LOOP
                if(video1.frameGrabber.frameNumber == video1.frameGrabber.lengthInFrames-2) {
                    video1.frameGrabber.frameNumber = 0
                    video1.next()
                }
            }

            drawer.image(videoRT1.colorBuffer(0))
        }
    }
}
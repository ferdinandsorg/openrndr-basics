import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.ffmpeg.VideoPlayerFFMPEG

fun main() = application {

    val setWidth = 1280
    val setHeight = 720

    configure {
        width = setWidth
        height = setHeight
    }

    program {
        val webcam = VideoPlayerFFMPEG.fromDevice()  // default
//        val webcam = VideoPlayerFFMPEG.fromDevice("Built-in iSight") // mac
//        val webcam = VideoPlayerFFMPEG.fromDevice(deviceName="video=ChromaCam", width = 1280, height = 720, framerate = 50.0) // windows

        webcam.play()

        extend {
            drawer.background(ColorRGBa.BLACK)

            webcam.draw(drawer)
        }

    }
}
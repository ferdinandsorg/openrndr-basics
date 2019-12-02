import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.ffmpeg.FFMPEGVideoPlayer.Companion.defaultDevice
import org.openrndr.ffmpeg.FFMPEGVideoPlayer.Companion.fromDevice
import org.openrndr.ffmpeg.ScreenRecorder
import org.openrndr.math.Vector2

fun main() = application {
    // 4:3
    val setWidth = 640
    val setHeight = 480


    configure {
        width = setWidth*2
        height = setHeight
    }

    program {

//        extend(ScreenRecorder())

        val webcam1 = fromDevice("Built-in iSight", setWidth, setHeight, 30.000030)
        webcam1.start()

        val webcam2 = fromDevice("C922 Pro Stream Webcam", setWidth, setHeight, 30.000030)
        webcam2.start()

        extend {
            drawer.background(ColorRGBa.BLACK)


            webcam1.next()
            webcam1.draw(drawer)

            drawer.translate(setWidth.toDouble(), 0.0)
            webcam2.next()
            webcam2.draw(drawer)


        }

    }
}
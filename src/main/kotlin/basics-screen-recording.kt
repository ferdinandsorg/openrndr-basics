import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.ffmpeg.ScreenRecorder

fun main() = application {
    configure {
        width = 770
        height = 578
    }

    program {

        // Screen recording simple
//            extend(ScreenRecorder())

        // Screen recording expand
        extend(ScreenRecorder()) {
            frameRate = 30
        }

        extend {

            drawer.fill = ColorRGBa.PINK
            drawer.circle(mouse.position, 100.0)

        }
    }
}
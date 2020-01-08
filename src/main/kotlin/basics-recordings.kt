import org.openrndr.application
import org.openrndr.extensions.Screenshots
import org.openrndr.ffmpeg.ScreenRecorder

fun main() {
    application {
        configure {
            width = 770
            height = 578
        }
        program {

            // Screenshot
            extend(Screenshots())

            // Screen recordings
            extend(ScreenRecorder()) {
                frameRate = 30
            }

            extend {
                // do stuff
            }
        }
    }
}
import org.openrndr.KeyEvent
import org.openrndr.KeyEventType
import org.openrndr.application
import org.openrndr.extensions.Screenshots

fun main() =  application {
    program {
        extend(Screenshots())
        var lastTime = seconds
        extend {

            // triggers every 2 seconds
            if (seconds - lastTime > 2.0) {
                keyboard.keyDown.trigger(KeyEvent(KeyEventType.KEY_DOWN, ' '.toInt(), "space", emptySet()))
                lastTime = seconds
            }
        }
    }
}
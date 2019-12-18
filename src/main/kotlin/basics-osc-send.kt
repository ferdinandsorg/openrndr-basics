import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.BlendMode
import org.openrndr.draw.loadImage
import org.openrndr.extra.osc.OSC
import org.openrndr.math.Vector2
import org.openrndr.math.map
import java.net.InetAddress


fun main() = application {
    val setWidth = 640
    val setHeight = 480

    configure {
        width = setWidth
        height = setHeight
    }


    program {
        val osc = OSC(InetAddress.getByName("192.168.1.72"), 57110, 57110)

        val value1 = 20.0
        val value2 = "hello"

        extend {
            drawer.background(ColorRGBa.BLACK)

            drawer.fill = ColorRGBa.BLACK
            drawer.stroke = ColorRGBa.WHITE

            val inputValue = map(0.0, width.toDouble(), 40.0, width - 40.0, mouse.position.x)

            drawer.lineSegment(Vector2(40.0, height/2.0), Vector2(width - 40.0, height/2.0))
            drawer.circle(Vector2(mouse.position.x, height/2.0), 10.0)

            osc.send("/maxmsp/filter", listOf(inputValue))
        }

    }
}
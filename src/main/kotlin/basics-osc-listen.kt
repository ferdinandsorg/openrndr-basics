import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.BlendMode
import org.openrndr.draw.loadImage
import org.openrndr.extra.osc.OSC
import org.openrndr.math.Vector2
import java.net.InetAddress


fun main() = application {
    val setWidth = 640
    val setHeight = 480

    configure {
        width = setWidth
        height = setHeight
    }


    var firstValue: Float = 0F
    var secondValue: Float = 0F

    program {
        val osc = OSC(InetAddress.getByName("192.168.1.72"), 57110, 57110)
        osc.listen("/live/track2") { it ->
            // -- get the first value
            firstValue = it[0] as Float
            secondValue = it[1] as Float

        }

        extend {
            drawer.background(ColorRGBa.BLUE)


            println("firstValue is $firstValue")
            println("secondValue is $secondValue")
        }

    }
}
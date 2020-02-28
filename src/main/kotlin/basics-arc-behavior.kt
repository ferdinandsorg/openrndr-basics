import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.gui.GUI
import org.openrndr.extra.noise.valueLinear
import org.openrndr.extra.parameters.BooleanParameter
import org.openrndr.extra.parameters.DoubleParameter
import org.openrndr.ffmpeg.ScreenRecorder
import org.openrndr.math.Vector2
import org.openrndr.math.map
import org.openrndr.math.mix
import org.openrndr.shape.contour
import org.openrndr.shape.shape
import java.lang.Math.cos
import java.lang.Math.sin


fun main() = application {
    configure {
        width = 500
        height = 500
    }

    program {

        val gui = GUI()
        val settings = object {
            @DoubleParameter("x1", 0.0, 500.0)
            var x1: Double = 250.0

            @DoubleParameter("y1", 0.0, 500.0)
            var y1: Double = 250.0

            @DoubleParameter("x2", 0.0, 500.0)
            var x2: Double = 250.0

            @DoubleParameter("y2", 0.0, 500.0)
            var y2: Double = 250.0

            @DoubleParameter("rangeOnLine", 0.0, 1.0)
            var rangeOnLine: Double = 0.5

            @BooleanParameter("arcFlag")
            var arcFlag = false

            @BooleanParameter("sweepFlag")
            var sweepFlag: Boolean = false
        }
        gui.add(settings, "Settings")
        extend(gui)

        extend {
            drawer.background(ColorRGBa.BLACK)
            drawer.stroke = ColorRGBa.PINK
            drawer.fill = null

            val x1 = settings.x1
            val x2 = settings.x2
            val y1 = settings.y1
            val y2 = settings.y2

            val crx = mix(x1, x2, settings.rangeOnLine)
            val cry = mix(y1, y2, settings.rangeOnLine)

            val c = contour {
                moveTo(x1, y1)
                arcTo(crx, cry, 0.0, settings.arcFlag, settings.sweepFlag, x2, y2)
            }
            drawer.contour(c)

            drawer.fill = ColorRGBa.YELLOW
            drawer.circle(x1, y1, 5.0)

            drawer.fill = ColorRGBa.BLUE
            drawer.circle(x2, y2, 5.0)

            drawer.fill = ColorRGBa.GREEN
            drawer.circle(crx, cry, 5.0)
        }
    }
}
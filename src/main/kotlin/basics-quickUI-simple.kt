import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.gui.GUI
import org.openrndr.extra.parameters.DoubleParameter

fun main() = application {
    val setWidth = 900
    val setHeight = 600

    configure {
        width = setWidth
        height = setHeight
    }

    program {
        val gui = GUI()
        val settings = object {
            @DoubleParameter("x", 0.0, 900.0)
            var x: Double = width/2.0

            @DoubleParameter("y", 0.0, 600.0)
            var y: Double = height/2.0

            @DoubleParameter("size", 5.0, 100.0)
            var size: Double = 5.0
        }

        gui.add(settings, "Settings")

        // -- pitfall: the extend has to take place after gui is populated
        extend(gui)

        extend {
            drawer.background(ColorRGBa.BLACK)

            drawer.fill = ColorRGBa.PINK
            drawer.circle(settings.x, settings.y, settings.size)
        }
    }
}
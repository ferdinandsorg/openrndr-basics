import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.shape.Rectangle

fun main() = application {
    configure {
        width = 640
        height = 480
    }

    program { // setup

        val grid = mutableListOf<Rectangle>()
        for (x in 0 until 10) {
            for (y in 0 until 10) {
                grid.add(
                    Rectangle(
                        x * 65.0,
                        y * 49.0,
                        55.0,
                        39.0
                    )
                )
            }
        }

        extend { // draw
            drawer.background(ColorRGBa.BLACK)

            drawer.stroke = null
            drawer.fill = ColorRGBa.PINK

            drawer.rectangles(grid)

        }

    }
}
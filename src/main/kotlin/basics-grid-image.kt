import org.openrndr.animatable.Animatable
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.ColorBuffer
import org.openrndr.draw.isolated
import org.openrndr.draw.loadFont
import org.openrndr.draw.loadImage
import org.openrndr.math.Vector2
import org.openrndr.math.map
import org.openrndr.shape.Rectangle

fun main() = application {
    configure {
        width = 640
        height = 480
    }

    program {

        val images = mutableListOf<ColorBuffer>()
        for (img in 0 until 5) {
            images.add(loadImage("data/images/example-img/img_$img.jpg"))
        }

        val font = loadFont("data/fonts/IBMPlexMono-Regular.ttf", 16.0)

        val columns = 10.0
        val rows = 10.0
        val margin = 5.0

        class RectGrid(var x: Double, var y: Double, var w: Double, var h: Double, var column: Int, var row: Int) : Animatable() {
        }

        val grid = mutableListOf<RectGrid>()
        for (gX in 0 until columns.toInt()) {
            for (gY in 0 until rows.toInt()) {
                grid.add(
                    RectGrid(
                        gX * (width/columns) + margin,
                        gY * (height/rows) + margin,
                        (width/columns) - margin*2,
                        (height/rows)  - margin*2,
                        gX,
                        gY
                    )
                )
            }
        }


        extend {
            drawer.background(ColorRGBa.WHITE)
            drawer.stroke = null
            drawer.fill = ColorRGBa.BLACK
            drawer.fontMap = font

            grid.forEachIndexed { index, it ->

                // calculate chronological order of images
                val i = ((it.row * columns) + it.column).toInt()

                if (i < images.size) {

                    // draw as much images as you have in images Array
                    drawer.image(images[i], it.x, it.y, it.w, it.h)

                } else {

                    // if you ran out of images draw rectangeles
                    drawer.rectangle(it.x, it.y, it.w, it.h)

                }

                // draw numbers
                drawer.isolated {
                    drawer.fill = ColorRGBa.GREEN
                    drawer.text(i.toString(), it.x + 6.0,it.y + 16.0)
                }

            }

        }

    }
}
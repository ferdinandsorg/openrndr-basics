import kotlinx.coroutines.delay
import org.openrndr.animatable.Animatable
import org.openrndr.animatable.easing.Easing
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.math.Vector2
import org.openrndr.math.map
import kotlin.math.sin

fun main() = application {
    configure {
        width = 640
        height = 480
    }

    program { // setup

        val rows = 10.0
        val columns = 10.0
        val margin = 10.0
        val items = rows*columns

        class RectGrid(var x: Double, var y: Double, var w: Double, var h: Double, var o: Double, var count: Double) : Animatable() {

            var setOpacity = this.o

            init {
                this.update()
            }

            val duration = 3000L

            fun update() {
                animate("o", 1.0, duration, Easing.CubicInOut)
                delay(((count%items)*100).toLong())
                animate("o", 0.0, duration, Easing.CubicInOut)
                complete()
            }

        }

        val grid = mutableListOf<RectGrid>()

        var count = 0.0

        for (gX in 0 until rows.toInt()) {
            for (gY in 0 until columns.toInt()) {
                grid.add(
                    RectGrid(
                        gX * (width/rows) + margin,
                        gY * (height/columns) + margin,
                        (width/rows) - margin*2,
                        (height/columns)  - margin*2,
                        Math.random(),
                        count++
                    )
                )
            }
        }

        extend { // draw
            drawer.stroke = null

            grid.forEachIndexed { index, it ->
                it.updateAnimation()
                it.update()


                drawer.fill = ColorRGBa.PINK.opacify(it.o)
                drawer.rectangle(it.x, it.y, it.w, it.h)
            }

        }

    }
}
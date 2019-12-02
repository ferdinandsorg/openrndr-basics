import org.openrndr.animatable.Animatable
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

        var columnWidth = 10.0
        var columnHeight = 10.0
        var margin = 10.0

        class RectGrid(var x: Double, var y: Double, var w: Double, var h: Double, var o: Double) : Animatable() {

            init {
                this.update(Vector2(0.0,0.0))
            }

            fun update(mouse: Vector2) {
//                columnWidth = mouse.x
            }
        }

        val grid = mutableListOf<RectGrid>()

        for (gX in 0 until columnWidth.toInt()) {
            for (gY in 0 until columnHeight.toInt()) {
                grid.add(
                    RectGrid(
                        gX * (width/columnWidth) + margin,
                        gY * (height/columnHeight) + margin,
                        (width/columnWidth) - margin*2,
                        (height/columnHeight)  - margin*2,
                        Math.random()
                    )
                )
            }
        }

        extend { // draw
            drawer.stroke = null

            grid.forEachIndexed { index, it ->
                it.update(mouse.position)
                it.updateAnimation()



                var newX = map(0.0,  width.toDouble(), it.x, mouse.position.x, mouse.position.x)
                var newY = map(0.0, height.toDouble(), it.y, mouse.position.y, mouse.position.y)

                drawer.fill = ColorRGBa.PINK.opacify(it.o)
                drawer.rectangle(newX,it.y + newY,it.w,it.h)

            }

        }

    }
}
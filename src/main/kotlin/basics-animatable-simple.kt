import org.openrndr.animatable.Animatable
import org.openrndr.animatable.easing.Easing
import org.openrndr.application
import org.openrndr.color.ColorRGBa

fun main() = application {
    val setWidth = 800
    val setHeight = 800

    configure {
        width = setWidth
        height = setHeight
    }

    class Moving(var x:Double, var y:Double) : Animatable() {

        fun update() {

            animate("y", Math.random() * setHeight, 1000L, Easing.CubicInOut)
            complete()

        }
    }

    program {

        val movingRects = mutableListOf<Moving>()
        for (x in 0 until 10) {
            for (y in 0 until 1) {
                movingRects.add(Moving(x * 100.0, 0.0))
            }
        }


        extend {
            drawer.background(ColorRGBa.BLACK)

            drawer.stroke = null
            drawer.fill = ColorRGBa.PINK

            movingRects.forEach { it
                it.updateAnimation()
                it.update()

                drawer.rectangle(it.x, it.y, 100.0, 50.0)
            }

        }
    }
}
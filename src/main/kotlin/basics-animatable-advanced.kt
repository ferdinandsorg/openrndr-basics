import org.openrndr.animatable.Animatable
import org.openrndr.animatable.easing.Easing
import org.openrndr.application
import org.openrndr.color.ColorRGBa

fun main() = application {
    val setWidth = 800
    val setHeight = 800

    val countLimit = 5
    val elementSize = 50.0

    configure {
        width = setWidth
        height = setHeight
    }

    class Moving(var x:Double, var homeY:Double) : Animatable() {
        var y = homeY

        var count = 0
        var state1 = true
        var state2 = false

        val d = 1000L

        fun update() {

            if(count == countLimit) {
                state2 = true
                state1 = false
            }

            if (state1) {
                if (!hasAnimations()) {

                    animate("y", Math.random() * setHeight, d, Easing.CubicInOut)
                    complete {
                        count++
                    }

                }
            }

            if(state2) {
                if(!hasAnimations()) {

                    animate("y", homeY, d, Easing.CubicInOut)
                    complete()
                }
            }
        }
    }

    program {

        val movingRects = mutableListOf<Moving>()
        for (x in 0 until 10) {
            for (y in 0 until 1) {
                movingRects.add(Moving(x * 100.0, setHeight/2.0))
            }
        }

        extend {
            drawer.background(ColorRGBa.BLACK)

            drawer.stroke = null
            drawer.fill = ColorRGBa.PINK

            movingRects.forEach { it
                it.updateAnimation()
                it.update()

                drawer.circle(it.x, it.y, elementSize)
            }

        }
    }
}
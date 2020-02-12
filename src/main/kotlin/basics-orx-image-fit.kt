import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.ColorBuffer
import org.openrndr.draw.isolated
import org.openrndr.draw.loadImage
import org.openrndr.extras.imageFit.FitMethod
import org.openrndr.extras.imageFit.imageFit
import org.openrndr.math.map
import org.openrndr.panel.ControlManager
import org.openrndr.panel.elements.*
import org.openrndr.panel.layout
import org.openrndr.shape.Rectangle
import kotlin.math.sin

fun main() = application {
    val setWidth = 300
    val setHeight = 300

    configure {
        width = setWidth*3
        height = setHeight*3
    }

    program {

        // load images
        val landscape = loadImage("data/images/example-landscape.jpg")
        val portrait = loadImage("data/images/example-portrait.jpg")
        val square = loadImage("data/images/example-square.jpg")


        var valueHorizontalPosition = 0.0
        var valueVerticalPosition = 0.0
        var valueFitMethod = FitMethod.Cover

        extend(ControlManager()) {
            layout {

                dropdownButton {
                    label = "scale Type"

                    item {
                        label = "cover"
                        events.picked.subscribe {
                            valueFitMethod = FitMethod.Cover
                        }
                    }

                    item {
                        label = "contain"
                        events.picked.subscribe {
                            valueFitMethod = FitMethod.Contain
                        }
                    }
                }

                slider {
                    label = "horizontalPosition"
                    value = 0.0
                    range = Range(-1.0, 1.0)
                    precision = 2
                    events.valueChanged.subscribe {
                        valueHorizontalPosition = it.newValue
                    }
                }

                slider {
                    label = "vVerticalPosition"
                    value = 0.0
                    range = Range(-1.0, 1.0)
                    precision = 2
                    events.valueChanged.subscribe {
                        valueVerticalPosition = it.newValue
                    }
                }
            }
        }

        extend {
            drawer.background(ColorRGBa.WHITE)

            val moveX = if (mouse.position.x > 0.0 && mouse.position.x < setWidth) {
                mouse.position.x
            } else {
                300.0
            }

            val moveY = if (mouse.position.y > 0.0 && mouse.position.y < height.toDouble()) {
                mouse.position.y
            } else {
                300.0
            }

            drawer.imageFit(portrait, 10.0, 300.0, moveX, moveY, FitMethod.Cover, valueHorizontalPosition, valueVerticalPosition)

            drawer.imageFit(landscape, 320.0, 300.0, 300.0, 300.0, FitMethod.Cover, sin(seconds), 0.0)

            drawer.imageFit(square, 630.0, 300.0, 100.0, 300.0, valueFitMethod, valueHorizontalPosition, valueVerticalPosition)

        }

    }
}


import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.shape.Rectangle


fun main() = application {
    val setWidth = 640
    val setHeight = 480

    configure {
        width = setWidth
        height = setHeight
    }


    program {

        // load image
        val img = loadImage("data/images/image-example-640x480.jpg")

        extend {

            // draw image
            drawer.image(img,
                Rectangle(0.0, 0.0, width.toDouble(), height.toDouble()), // original size
                Rectangle(0.0,0.0, 100.0, 75.0) // new size
            )

        }

    }
}
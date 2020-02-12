import org.openrndr.application
import org.openrndr.draw.loadImage
import org.openrndr.shape.Rectangle

fun main() = application {
    configure {
        width = 640
        height = 480
    }

    program {

        // load image
        val img = loadImage("data/images/image-example-640x480.jpg")

        extend {

            // draw image
            drawer.image(img,
                Rectangle(0.0, 0.0, 640.0, 480.0), // original size
                Rectangle(0.0,0.0, 100.0, 75.0) // new size
            )

        }

    }
}
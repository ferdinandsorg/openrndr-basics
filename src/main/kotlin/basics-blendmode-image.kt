import org.openrndr.application
import org.openrndr.draw.BlendMode
import org.openrndr.draw.loadImage


fun main() = application {
    val setWidth = 640
    val setHeight = 480

    configure {
        width = setWidth
        height = setHeight
    }


    program {

        // load image
        val img1 = loadImage("data/images/pm5544.png")
        val img2 = loadImage("data/images/image-example-640x480.jpg")

        extend {

            // draw 1st image
            drawer.image(img1, 0.0, 0.0)

            // draw blend mode multiply
            drawer.drawStyle.blendMode = BlendMode.MULTIPLY

            // draw 2nd image
            drawer.image(img2, 0.0, 0.0)

        }

    }
}
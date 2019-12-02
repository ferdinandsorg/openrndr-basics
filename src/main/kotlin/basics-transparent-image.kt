import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*


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

            // draw transparancy
            drawer.drawStyle.colorMatrix = tint(ColorRGBa.WHITE.opacify(0.5))

            // draw image
            drawer.image(img, 0.0, 0.0)
        }

    }
}
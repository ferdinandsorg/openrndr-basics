import org.openrndr.application
import org.openrndr.draw.*


fun main() = application {
    val setWidth = 640
    val setHeight = 480

    configure {
        width = setWidth*2
        height = setHeight
    }


    program {

        val images = mutableListOf<ColorBuffer>()
        for (i in 0 until 2) {
            images.add(loadImage("data/images/image-example-640x480.jpg"))
        }

        val rt = renderTarget(setWidth, setHeight) {
            colorBuffer()
        }

        extend {
            // generate new image
            drawer.isolatedWithTarget(rt) {
                ortho(rt)
                drawer.drawStyle.colorMatrix = grayscale(1.0,0.0,0.0)
                drawer.image(images[0], 0.0,0.0, setWidth.toDouble(), setHeight.toDouble())
            }

            // put new image in list of images
            images[1] = rt.colorBuffer(0)

            // draw original image
            drawer.image(images[0], 0.0,0.0)

            // draw new image
            drawer.image(images[1], setWidth.toDouble(),0.0)
        }

    }
}
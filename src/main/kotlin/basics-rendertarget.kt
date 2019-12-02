import org.openrndr.application
import org.openrndr.draw.grayscale
import org.openrndr.draw.isolatedWithTarget
import org.openrndr.draw.loadImage
import org.openrndr.draw.renderTarget
import org.openrndr.ffmpeg.ScreenRecorder


fun main() = application {
    val setWidth = 640
    val setHeight = 480

    configure {
        width = setWidth*2
        height = setHeight
    }


    program {

        // load image
        val img = loadImage("data/images/image-example-640x480.jpg")

        // initialize render target
        val rt = renderTarget(setWidth, setHeight) {
            colorBuffer()
        }

        extend {
            // generate new image
            drawer.isolatedWithTarget(rt) {
                ortho(rt)
                drawer.drawStyle.colorMatrix = grayscale(1.0,0.0,0.0)
                drawer.image(img, 0.0,0.0, img.width.toDouble(), img.height.toDouble())
            }

            // draw new image
            drawer.image(rt.colorBuffer(0), 0.0,0.0)

            // draw original image
            drawer.image(img, img.width.toDouble(), 0.0)
        }

    }
}
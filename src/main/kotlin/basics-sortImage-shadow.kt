import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.ffmpeg.ScreenRecorder
import org.openrndr.math.map
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sin


fun main() = application {
    val setWidth = 900
    val setHeight = 600

    configure {
        width = setWidth*2
        height = setHeight
    }


    program {

        // load image
        val img = loadImage("data/images/imm031_31_900x600.jpg")

        // initialize render target
        val inputRT = renderTarget(setWidth, setHeight) {
            colorBuffer()
        }

        val sortRT = renderTarget(setWidth, setHeight) {
            colorBuffer()
        }

        extend {
            drawer.background(ColorRGBa.BLACK)

            drawer.isolatedWithTarget(inputRT) {
                ortho(inputRT)
                drawer.image(img, 0.0,0.0, img.width.toDouble(), img.height.toDouble())
            }


            var inputShadow = inputRT.colorBuffer(0).shadow
            inputShadow.download()

            var i = 0
            var j = 0
            for (x in 0 until (inputRT.width)) {
                for (y in 0 until (inputRT.height)) {

                    if(inputShadow[x, y].r > 0.5) {
                        inputShadow.set((Math.random() * x).toInt(), (Math.random() * y).toInt(), ColorRGBa(1.0, 0.0, 0.0, 1.0))
                    } else {
                        inputShadow.set(x, y, ColorRGBa(0.0, 0.0, 0.0, 1.0))
                    }

                    i++
                }
                j++
            }
            inputShadow.upload()

            drawer.image(inputShadow.colorBuffer, 0.0, 0.0)
            drawer.image(img, setWidth.toDouble(), 0.0)

        }

    }
}
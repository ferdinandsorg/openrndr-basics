package examples.`05_Drawing_and_transformations`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.BufferMultisample
import org.openrndr.ffmpeg.ScreenRecorder

fun main(args: Array<String>) {
    application {
        configure {
            width = 770
            height = 578
        }
        program {

            extend {
                drawer.fill = ColorRGBa.PINK
                drawer.stroke = null

                var x = 200.0
                var y = 200.0
                var rWidth = 100.0
                var rHeight = 100.0

                // -- translate
//                drawer.translate(width/2.0, height/2.0)
                // -- rotate
                drawer.rotate(seconds * x)
                // -- rectangle around the origin
                drawer.rectangle(-(rWidth/2), -(rHeight/2), rWidth, rHeight)
            }
        }
    }
}
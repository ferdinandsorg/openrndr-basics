import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.isolatedWithTarget
import org.openrndr.draw.loadImage
import org.openrndr.draw.renderTarget
import org.openrndr.extra.runway.*
import org.openrndr.ffmpeg.ScreenRecorder
import org.openrndr.shape.Rectangle

fun main() = application {
    configure {
        width = 640
        height = 1138
    }

    program {

//        extend(ScreenRecorder())

        val imageInput = loadImage("data/images/hi-example_3.jpg")

        val scaleFactor = width/imageInput.width.toDouble()

        val rt = renderTarget(imageInput.width, imageInput.height) {
            colorBuffer()
        }



//        val videoInput = VideoPlayerFFMPEG.fromFile("data/videos/video-example-900x600.mp4")
//        videoInput.play()


        var detectionResult: PsenetResult? = null

        extend {
            drawer.isolatedWithTarget(rt) {
                drawer.background(ColorRGBa.BLACK)
                drawer.ortho(rt)
                drawer.image(imageInput, 0.0, 0.0)

//                videoInput.draw(drawer)
            }

            // -- Input image
            drawer.image(rt.colorBuffer(0))

            if (detectionResult == null) {
                detectionResult =
                    runwayQuery("http://localhost:8000/query", PsenetRequest(rt.colorBuffer(0).toData()))
            }


            detectionResult?.let { result ->

                val detectedText = result.bboxes

                detectedText.forEachIndexed { index, bbox ->

                    drawer.stroke = ColorRGBa.RED
                    drawer.fill = null

                    val x = bbox[0] * imageInput.width
                    val y = bbox[1] * imageInput.height
                    val w = (bbox[2] * imageInput.width) - x
                    val h = (bbox[3] * imageInput.height) - y

//                println("x is $x, y is $y, w is $w, h is $h")

//                var mouseX = map(0.0, width.toDouble(), 0.0, detectedText.size.toDouble(), mouse.position.x).toInt()

                    val t = seconds.toInt() % detectedText.size

                    if (index == t) {

                        drawer.rectangle(x, y, w, h)

                        drawer.image(
                            rt.colorBuffer(0),
                            Rectangle(x, y, w, h),
                            Rectangle(width/2.0 - (w / 2.0), imageInput.height + ((height - imageInput.height)/2) - (h / 2.0), w, h)
                        )
                    }


//                println("_____________________")
                }


            }

        }
    }
}
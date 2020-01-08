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
        width = 900 * 2
        height = 900
    }

    program {

//        extend(ScreenRecorder())

        val imageInput = loadImage("data/images/hi-example.jpg")

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

                    val t = seconds.toInt() % detectedText.size

                    if (index == t) {

                        drawer.rectangle(x, y, w, h)

                        drawer.image(
                            rt.colorBuffer(0),
                            Rectangle(x, y, w, h),
                            Rectangle(1350.0 - (w / 2.0), 450.0 - (h / 2.0), w, h)
                        )

                    }
                }

            }

        }
    }
}
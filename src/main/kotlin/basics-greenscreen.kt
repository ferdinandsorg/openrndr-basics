import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.ffmpeg.FFMPEGVideoPlayer
import org.openrndr.ffmpeg.ScreenRecorder
import org.openrndr.ffmpeg.VideoPlayerFFMPEG
import org.openrndr.filter.blend.passthrough
import org.openrndr.math.map
import org.openrndr.shape.Rectangle
import java.nio.ByteBuffer
fun main() = application {
    configure {
        width = 1920
        height = 1080
    }
    program {
        var videoPlayer = VideoPlayerFFMPEG.fromDevice()  // default
        videoPlayer.play()
        val vdRT = renderTarget(1280, 720) {
            colorBuffer()
        }
        val vdRTS = renderTarget(1280, 720) {
            colorBuffer()
        }
        var ticker = 0
        drawer.isolatedWithTarget(vdRTS) {
            drawer.background(ColorRGBa.BLACK)
        }
        extend {
            drawer.isolatedWithTarget(vdRT) {
                drawer.ortho(vdRT)
                videoPlayer.draw(drawer)
            }
            drawer.isolatedWithTarget(vdRTS) {
                ortho(vdRTS)
                drawer.fill = ColorRGBa.BLACK.opacify(0.01)
                drawer.rectangle(0.0, 0.0, 1280.0, 720.0)
                val textT = vdRT.colorBuffer(0)
                drawer.shadeStyle = shadeStyle {
                    fragmentTransform = """
                    vec2 textSize = textureSize(p_texture, 0);
                    vec2 uv = 1.0 - c_screenPosition /  textSize;
                    vec4 image = texture(p_texture, uv).rgba;
                    float alpha =  1.0;
                    if(image.g > 0.45 && image.r < 0.45 && image.b < 0.45) {
                        alpha = 0.0;
                    }
                    x_fill.rgba = vec4(image.r, image.g, image.b, alpha);
                    """.trimIndent()

                    parameter("texture", textT)
                    parameter("seconds", seconds)
                    parameter("width", width)
                    parameter("height", height)
                }

                drawer.rectangle(0.0, 0.0, 1280.0, 720.0)
            }
            drawer.image(vdRTS.colorBuffer(0), Rectangle(0.0, 0.0, 1280.0, 720.0), Rectangle(0.0, 0.0, 1920.0, 1080.0))
            ticker++
        }
    }
}
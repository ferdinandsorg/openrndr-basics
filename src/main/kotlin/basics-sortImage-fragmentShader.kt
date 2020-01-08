import org.openrndr.application
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
            // generate new image
            drawer.isolatedWithTarget(inputRT) {
                ortho(inputRT)
                drawer.drawStyle.colorMatrix = grayscale(1.0,0.0,0.0)
                drawer.image(img, 0.0,0.0, img.width.toDouble(), img.height.toDouble())
            }

            drawer.isolatedWithTarget(sortRT) {
                ortho(sortRT)

                val shadow = inputRT.colorBuffer(0)

                drawer.shadeStyle = shadeStyle {

                    fragmentPreamble = """
                        #define PI 3.14159265359
                        #define TWO_PI 6.28318530718
                        #define FOUR_PI 12.56637061436
                        
                        vec3 hsb2rgb( in vec3 c ) {
                            vec3 rgb = clamp(abs(mod(c.x*6.0+vec3(0.0,4.0,2.0), 6.0)-3.0)-1.0, 0.0, 1.0);
                            rgb = rgb*rgb*(3.0-2.0*rgb);
                            return c.z * mix( vec3(1.0), rgb, c.y);
                        }
                    """.trimIndent()

                    fragmentTransform = """
                        vec2 size = textureSize(p_shadow, 0);
                        vec2 u_resolution = size.xy;
                        vec2 uv = 1.0 - c_screenPosition / size;
                        vec2 st = gl_FragCoord.xy/u_resolution.xy;
                        vec3 imgSourceT = texture(p_shadow, uv).rgb;
                        
                        vec2 toCenter = vec2(0.5,0.5)-st;
                        float angle = atan(toCenter.x,toCenter.y);
                        float radius = length(toCenter)*2.0;
                        vec3 color = vec3(1.0);
                        
                        color = hsb2rgb(vec3((angle/TWO_PI)+p_timeSec,radius,0.8));
                        if(imgSourceT.r < color[0] && imgSourceT.g < color[1] && imgSourceT.b < color[2]) {
                            color = vec3(color);
                        } else {
                            color = vec3(1.0);
                        }
                        x_fill = vec4(color, 0.01);
                    """
                    parameter("width", width)
                    parameter("height", height)
                    parameter("shadow", shadow)
                    parameter("mouseX", map(0.0,width.toDouble(),0.0,1.0,mouse.position.x))
                    parameter("mouseY", map(0.0,height.toDouble(),0.0,1.0,mouse.position.y))
                    parameter("time", abs(sin(seconds)))
                    parameter("timeSec", map(0.0,7.0,0.0,1.0,seconds%7.0))
                    parameter("timePI", map(0.0, 7.0,0.0, PI,seconds%7.0))
                }

                drawer.rectangle(0.0, 0.0, width.toDouble(),height.toDouble())
            }

            // draw new image
            drawer.image(sortRT.colorBuffer(0), 0.0,0.0)

            // draw original image
            drawer.image(img, img.width.toDouble(), 0.0)
        }

    }
}
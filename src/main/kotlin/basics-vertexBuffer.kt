import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.math.Vector3

fun main() = application {
    val setWidth = 640
    val setHeight = 480

    configure {
        width = setWidth
        height = setHeight
    }

    program {
        val geometry = vertexBuffer(vertexFormat {
            position(3)
        }, 3 * 100)

        geometry.put {
            for (i in 0 until geometry.vertexCount) {
                write(Vector3(i * (width/geometry.vertexCount).toDouble(),Math.random() * height, 0.0))
            }
        }

        extend {
            drawer.shadeStyle = shadeStyle {
                fragmentTransform = """
                    
                    x_fill.rgb *= vec3(0.1,0.7,0.6);
                    
                    
                """.trimMargin()
            }

            drawer.fill = ColorRGBa.PINK.opacify(0.5)
            drawer.vertexBuffer(geometry, DrawPrimitive.LINES)
        }
    }
}
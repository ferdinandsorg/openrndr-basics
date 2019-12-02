import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.math.Vector3

fun main() = application {
    // 4:3
    val setWidth = 640
    val setHeight = 480

    configure {
        width = setWidth
        height = setHeight
    }

    program {
        val geometry = vertexBuffer(vertexFormat {
            position(3)
//            normal(3)
//            color(4)
//            attribute("objectID", VertexElementType.FLOAT32)
        }, 3 * 100)

        geometry.put {
            for (i in 0 until geometry.vertexCount) {
//                write(Vector3(Math.random() * width, Math.random() * height, 0.0))
//                write(Vector3(Math.random() * width, i * (height/geometry.vertexCount).toDouble(), 0.0))
                write(Vector3(i * (width/geometry.vertexCount).toDouble(),Math.random() * height, 0.0))
            }

//            write(Vector3(50.0, 50.0, 0.0))
//            write(Vector3(200.0, 100.0, 0.0))
//
//            write(Vector3(300.0, 300.0, 0.0))
//            write(Vector3(300.0, 350.0, 0.0))
        }

        extend {
            drawer.shadeStyle = shadeStyle {
//                fragmentTransform = """x_fill.rgb = vec3(0.1,0.1,0.6);"""
                fragmentTransform = """
                    
                    x_fill.rgb *= vec3(0.1,0.7,0.6);
                    
                    
                """.trimMargin()
//                fragmentTransform = """x_fill.rgb *= vec3(x_fill.r, x_fill.r, x_fill.r);"""

//                vertexTransform = "x_viewMatrix = x_viewMatrix * i_transform;"
            }

            drawer.fill = ColorRGBa.PINK.opacify(0.5)
//            drawer.vertexBuffer(geometry, DrawPrimitive.TRIANGLES)
            drawer.vertexBuffer(geometry, DrawPrimitive.LINES)

//            drawer.fill = ColorRGBa.YELLOW
//            drawer.rectangle(200.0,200.0, 300.0,100.0)
        }
    }
}
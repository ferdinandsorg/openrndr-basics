import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.math.Vector3
import org.openrndr.math.transforms.transform

fun main() = application {
    // 4:3
    val setWidth = 640
    val setHeight = 480

    configure {
        width = setWidth
        height = setHeight
    }

    program {
        // -- create the vertex buffer
        val geometry = vertexBuffer(vertexFormat {
            position(3)
        }, 4)

        // -- fill the vertex buffer with vertices for a unit quad
        geometry.put {
            write(Vector3(-1.0, -1.0, 0.0))
            write(Vector3(-1.0, 1.0, 0.0))
            write(Vector3(1.0, -1.0, 0.0))
            write(Vector3(1.0, 1.0, 0.0))
        }

        // -- create the secondary vertex buffer, which will hold transformations
        val transforms = vertexBuffer(vertexFormat {
            attribute("transform", VertexElementType.MATRIX44_FLOAT32)
        }, 30)

        // -- fill the transform buffer
        transforms.put {
            for (i in 0 until 30) {
                write(transform {
                    translate(i * (width/30.0), Math.random() * height)
                    rotate(Vector3.UNIT_Z, Math.random() * 360.0)
                    scale(Math.random() * 30.0)
                })
            }
        }
        extend {
            drawer.fill = ColorRGBa.PINK.opacify(0.25)
            drawer.shadeStyle = shadeStyle {
                vertexTransform = "x_viewMatrix = x_viewMatrix * i_transform;"
            }
            drawer.vertexBufferInstances(listOf(geometry), listOf(transforms), DrawPrimitive.TRIANGLE_STRIP, 1000)
        }
    }
}
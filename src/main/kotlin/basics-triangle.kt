import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.math.Vector2
import org.openrndr.shape.contour

fun main() = application {

    val setWidth = 500
    val setHeight = 500

    configure {
        width = setWidth
        height = setHeight
    }

    program {

        extend {
            drawer.background(ColorRGBa.WHITE)
            drawer.stroke = ColorRGBa.BLACK

            val taille = 50.0

            // p5
            // triangle(0, taille, taille, taille, taille, 0)
//            val triangle = contour {
//                moveTo(Vector2(taille, 0.0))
//                lineTo(cursor + Vector2(0.0, taille))
//                lineTo(cursor + Vector2(-taille, 0.0))
//                lineTo(anchor)
//                close()
//            }

            // p5
            // triangle(0, 0, taille, 0, 0, taille)
            val triangle = contour {
                moveTo(Vector2(0.0, 0.0))
                lineTo(cursor + Vector2(taille, 0.0))
                lineTo(cursor + Vector2(-taille, taille))
                lineTo(anchor)
                close()
            }


            drawer.contour(triangle)

        }

    }
}
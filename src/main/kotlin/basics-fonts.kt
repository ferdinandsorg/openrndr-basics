import com.google.gson.Gson
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.ffmpeg.ScreenRecorder
import java.awt.Font
import java.io.File


fun main() = application {
    val setWidth = 500
    val setHeight = 500

    configure {
        width = setWidth
        height = setHeight
    }

    // -- get JSON File
    val gson = Gson()
    val jsonString = File("data/fontList.json").readText()
    val fontList = gson.fromJson(jsonString, allFont::class.java)


    program {

        // load image
        val img = loadImage("data/images/image-example-640x480.jpg")

        var font1 = loadFont("data/fonts/fontSet/1.ttf", 100.0)


//        var mFontList = mutableListOf<FontMap>()
//        for (i in 0 .. 10) {
//            mFontList.add(loadFont("${fontList.fonts[i].fontLink}", 500.0))
//        }

        var mFontList = mutableListOf<FontMap>()
        for (i in 0 until 10) {
            mFontList.add(loadFont("data/fonts/fontSet/f$i.ttf", 500.0))
        }




//        val fonts = mutableListOf<FontImageMap>()
//        for (f in 0 until 50) {
//            fonts.add(loadFont("data/fonts/IBMPlexMono-Regular.ttf"))
//        }

        // initialize render target
        val rt = renderTarget(setWidth, setHeight) {
            colorBuffer()
        }

        extend {
            drawer.background(ColorRGBa.WHITE)



//            println("mFontList[4] is ${mFontList[4]}")


            drawer.isolatedWithTarget(rt) {
                ortho(rt)

                drawer.fill = ColorRGBa.BLACK


                drawer.fontMap = mFontList[3]
                drawer.text("HI", mouse.position)
            }

//            fontList.fonts.forEachIndexed { index, i ->
//
//                println("font of index $index is $i")
//
//            }

            // generate new image
//            drawer.isolatedWithTarget(rt) {
//                ortho(rt)
//
//                drawer.fontMap = font1
//
//                drawer.fill = ColorRGBa.BLACK
//                drawer.text("HI", 0.0, 350.0)
//            }

            // draw new image
            drawer.image(rt.colorBuffer(0), 0.0,0.0)
        }

    }
}

data class allFont(
    val fonts: List<Fonts>
)

data class Fonts(
    val fontID: Int,
    val fontLink: String
)
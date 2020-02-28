import org.openrndr.application

fun main() = application {

    program {

//        val listOfImages = listOf(
//            "/Users/ferdinandsorg/Desktop",
//            "/Users/ferdinandsorg/Documents",
//            "/Users/ferdinandsorg/Downloads",
//            "/Users/ferdinandsorg/IdeaProjects"
//        )

        val listOfImages = listOf(
            "/Users/ferdinandsorg/Desktop",
            "/Users/ferdinandsorg/Documents",
            "/Users/ferdinandsorg/Downloads",
            "/Users/ferdinandsorg/IdeaProjects",
            "/Volumes/Data\\ HD/\\#\\ Ferdinand",
            "/Volumes/GoogleDrive/My\\ Drive/Ferdinand"
        )
//        val a = Runtime.getRuntime().exec("open /Users/ferdinandsorg/Downloads/IMG_4536.JPG")


        listOfImages.forEachIndexed { index, i ->
//            println(i)

//            var a = Runtime.getRuntime().exec("find $i -name '*.**g' > /Users/ferdinandsorg/Downloads/getImagePath-${index}.txt")

//            val a = Runtime.getRuntime().exec("open /Users/ferdinandsorg/Downloads/IMG_4536.JPG")

//            val a = Runtime.getRuntime().exec("find $i -type f > FilesWithPaths00$index.txt")


            val commandPNG = "find $i -type f -name '*png' >> FilesWithPathsImages-all.txt"
            val commandJPG = "find $i -type f -name '*jpg' >> FilesWithPathsImages-all.txt"
            val commandJPEG = "find $i -type f -name '*jpeg' >> FilesWithPathsImages-all.txt"

//            val a = Runtime.getRuntime().exec(command)

//            println(a)
            println(commandPNG)
            println(commandJPG)
            println(commandJPEG)

        }
    }
}
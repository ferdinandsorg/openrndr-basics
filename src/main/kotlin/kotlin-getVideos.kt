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


            val commandMP4 = "find $i -type f -name '*mp4' >> FilesWithPathsVideos-all.txt"
            val commandMOV = "find $i -type f -name '*mov' >> FilesWithPathsVideos-all.txt"
            val commandMOV2 = "find $i -type f -name '*MOV' >> FilesWithPathsVideos-all.txt"

//            val a = Runtime.getRuntime().exec(command)

//            println(a)
            println(commandMP4)
            println(commandMOV)
            println(commandMOV2)

        }
    }
}
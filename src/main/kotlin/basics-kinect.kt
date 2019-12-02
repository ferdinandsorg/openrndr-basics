//import org.openrndr.application
//
//
//fun main() = application {
//    configure {
//        // default resolution of the Kinect v1 depth camera
//        width = 640
//        height = 480
//    }
//    program {
//        val kinects = getKinectsV1(this)
//        val kinect = kinects.startDevice()
//        kinect.depthCamera.enabled = true
//        extend {
//            drawer.image(kinect.depthCamera.currentFrame)
//        }
//    }
//}
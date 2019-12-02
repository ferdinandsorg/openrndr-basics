//import org.librealsense.*
//import org.openrndr.application
//import org.openrndr.color.ColorRGBa
//import org.openrndr.draw.*
//import org.openrndr.shape.Rectangle
//import studio.rndr.multisense.client.BlobTracker
//
//fun main() = application {
//    // 4:3
//    val setWidth = 1280
//    val setHeight = 720
//
//    configure {
//        width = setWidth
//        height = setHeight
//    }
//
//    program {
//
//        val pipelines = mutableListOf<Pipeline>()
//        val blobTrackers = mutableMapOf<Pipeline, BlobTracker>()
//        val colorBuffers = mutableMapOf<Pipeline, ColorBuffer>()
//        val intrinsicsX = mutableMapOf<Pipeline, Intrinsics>()
//
//        val context = Context.create()
//        context.queryDevices().devices.forEach {
//
//            if (it.name().toLowerCase().contains("real")) {
//
//                val sensors = it.querySensors()
//                for (i in 0 until sensors.sensorCount) {
//
//                    val sensor = sensors.createSensor(i)
//                    if (sensor.isExtendableTo(Native.Extension.RS2_EXTENSION_DEPTH_SENSOR)) {
//                        println(sensors.createSensor(i).depthScale)
//                    }
//                    sensor.destroy()
//                }
//                sensors.destroy()
//
//                val pipeline = context.createPipeline()
//                val config = Config.create()
//                config.enableDevice(it)
//                config.enableStream(Native.Stream.RS2_STREAM_DEPTH, 0, 640, 360, Native.Format.RS2_FORMAT_Z16, 30)
//                val profile = pipeline.startWithConfig(config)
//
//                val streams = profile.streams
//                for (i in 0 until streams.size) {
//
//                    val intrinsics = streams[i].videoStreamIntrinsics
//                    for (x in 0..4) {
//                        println("coeffs[$x]: ${intrinsics.coeffs[x]}")
//                    }
//                    intrinsicsX[pipeline] = intrinsics
//
//
//                }
//
//                pipelines.add(pipeline)
//                blobTrackers[pipeline] = BlobTracker(blobTrackers.size, intrinsicsX[pipeline]!!, endX = 640)
//                colorBuffers[pipeline] = colorBuffer(640, 360, 1.0, ColorFormat.R, ColorType.UINT16)
//            }
//        }
//
//        extend {
//            drawer.background(ColorRGBa.BLACK)
//
//            pipelines.forEach {
//
//                try {
//                    val frames = it.waitForFrames(1000)
//
//                    for (frameIndex in 0 until frames.frameCount()) {
//                        val frame = frames.frame(frameIndex)
//
//                        val data = frame.frameData
//                        colorBuffers[it]!!.write(data)
//
//                        data.rewind()
//
//                        blobTrackers[it]!!.update(frame.frameData.asShortBuffer())
//
//                        frame.release()
//
//                        drawer.isolated {
//                            drawer.shadeStyle = shadeStyle {
//                                fragmentTransform = """
//                            float d = x_fill.r * 65535.0;
//                            if (d > 9500.0) {
//                                x_fill.rgb = vec3(1.0, 1.0, 1.0);
//                            } else if (d < 00.0){
//                                x_fill.rgb = vec3(0.0, 1.0, 0.0);
//                            }
//                            else {
//                                x_fill.rgb*=50.0;
//                            }
//                        """
//                            }
//                            drawer.image(colorBuffers[it]!!, Rectangle(0.0, 360.0, 640.0, -360.0), Rectangle(0.0,0.0,width.toDouble(), height.toDouble()))
//                        }
//                        colorBuffers[it]!!.write(blobTrackers[it]!!.resultBytes)
//                    }
//
//                    frames.release()
//
//                } catch (e: RuntimeException) {
//                    e.printStackTrace()
//                    if (e.message == "wait_for_frames cannot be called before start()") {
//                        System.exit(1)
//                    }
//                }
//            }
//        }
//
//    }
//}
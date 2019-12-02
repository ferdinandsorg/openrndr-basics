//import com.sun.tools.javac.util.List
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.math.map
import org.openrndr.shape.Circle
import javax.sound.midi.*


fun main() = application {

    val setWidth = 1920
    val setHeight = 1080

    configure {
        width = setWidth/2
        height = setHeight/2
    }

    var channel = 0.0
    var value = 0.0
    var button = 0.0

    class MidiHandler {
        init {
            var device: MidiDevice
            val infos = MidiSystem.getMidiDeviceInfo()
            for (i in infos.indices) {
                try {
                    device = MidiSystem.getMidiDevice(infos[i])
                    println(infos[i])
                    val transmitters = device.transmitters
                    for (j in transmitters.indices) {
                        transmitters[j].receiver = MidiInputReceiver(device.deviceInfo.toString())
                    }
                    val trans = device.transmitter
                    trans.receiver = MidiInputReceiver(device.deviceInfo.toString())
                    device.open()
                    println(device.deviceInfo.toString() + " Was Opened")
                } catch (e: MidiUnavailableException) {
                }
            }
        }
        inner class MidiInputReceiver(var name: String) : Receiver {
            override fun send(msg: MidiMessage, timeStamp: Long) {

                button = msg.message.toList()[0].toDouble()
                channel = msg.message.toList()[1].toDouble()
                value = msg.message.toList()[2].toDouble()

            }
            override fun close() {
            }
        }
    }

    var m = MidiHandler()

    program {

        extend {

            // slider
            if (channel == 50.0) {
                var rectHeight = map(0.0,127.0, 0.0, 200.0, value)
                drawer.fill = ColorRGBa.WHITE
                drawer.rectangle(100.0,300.0-rectHeight,100.0,rectHeight)
            }

//            // rotate slider
//            if (channel == 6.0) {
//                var size = map(0.0,127.0, 5.0, 50.0, value)
//                drawer.fill = ColorRGBa.PINK
//                drawer.circle(300.0,150.0,size)
//            }

            // press rotate slider
            if (button == -112.0 && channel == 124.0) {
                drawer.fill = ColorRGBa.RED
                drawer.circle(600.0,150.0,50.0)
            }

            // press button
            if (button == -112.0 && channel == 14.0) {
                drawer.fill = ColorRGBa.BLUE
                drawer.circle(600.0,150.0,50.0)
            }

            // press button
            if (button == -112.0 && channel == 24.0) {
                drawer.fill = ColorRGBa.YELLOW
                drawer.circle(600.0,150.0,50.0)
            }

            // rotate slider
            if (channel == 6.0) {
                drawer.fill = ColorRGBa.WHITE
                drawer.stroke = ColorRGBa.PINK
                drawer.strokeWeight = 10.0
                var circleAngle = map(0.0,127.0, -Math.PI/2, Math.PI/2, value)

                val sub0 = Circle(185.0, height / 2.0, 100.0).contour.sub(0.0, 0.5 + 0.50 * Math.sin(circleAngle))
                drawer.contour(sub0)
            }
        }
    }
}
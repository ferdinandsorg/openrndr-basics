import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.midi.MidiDeviceDescription
import org.openrndr.extra.midi.MidiTransceiver
import org.openrndr.math.Vector2

// -- add "orx-midi" to the orxFeatures line in build.gradle.kts

fun main() = application {

    val setWidth = 1280
    val setHeight = 720

    configure {
        width = setWidth
        height = setHeight
    }

    program {

        // -- just needed to detect your MIDI Controller
        MidiDeviceDescription.list().forEach {
            println("name: '${it.name}', vendor: '${it.vendor}', receiver:${it.receive}, transmitter:${it.transmit}")
        }

        val values = mutableListOf<Int>()
        for (i in 0 .. 7) {
            values.add(0)
        }

        // -- set name and vendor with the function above
        val controller = MidiTransceiver.fromDeviceVendor("LPD8", "AKAI professional LLC")
        controller.controlChanged.listen {
            println("control change: channel: ${it.channel}, control: ${it.control}, value: ${it.value}")

            for (v in 0 .. 7) {
                if (it.control == v+1) {
                    values[v] = it.value
                }
            }
        }

        extend {
            drawer.background(ColorRGBa.BLACK)
            drawer.fill = ColorRGBa.WHITE

            for (i in 0 .. 7) {
                drawer.circle(Vector2(192.0 + 128.0 * i, height/2.0), values[i].toDouble())
            }

        }
    }
}
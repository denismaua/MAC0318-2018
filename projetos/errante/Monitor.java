/* reads Ultrasonic measurement and sends it via usb to pc */

import lejos.nxt.*;
import lejos.util.Delay;
import java.io.*;
import lejos.nxt.comm.*;

class Monitor {
    public static void main(String[] args) {

	UltrasonicSensor sonar = new UltrasonicSensor(SensorPort.S1);
	NXTConnection conn = USB.waitForConnection();
	DataOutputStream dataOut = conn.openDataOutputStream();
	while (true) {
	    int reading = sonar.getDistance();
	    try {
		dataOut.writeInt(reading);
	    } catch (IOException e) {
	    }
	}	
    }
}

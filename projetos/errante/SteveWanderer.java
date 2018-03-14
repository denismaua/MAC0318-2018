/* A robot that simply wanders around */

import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.*;
import lejos.util.Delay;
import java.util.Random;

class SteveWanderer {
    
    public static float WHEEL_SIZE = 5.6f; // wheel diameter (cm)
    public static float TRACK_SIZE = 11.5f; // track size (cm)
    public static float BK_DIST    = 10.0f; // distance to backtrack (cm)
    public static int   MOVE_SPEED =  10; // (wheel circumference/sec) 
    public static int   TURN_SPEED =  110; // (wheel turns/sec) 

    public static float MIN_DIST = 40.0f; // minimum distance to wall (cm)

    public static void main (String[] args){

	UltrasonicSensor sonar = new UltrasonicSensor(SensorPort.S1);
	
	// SteveWanderer robot = new SteveWanderer();
	DifferentialPilot pilot = new DifferentialPilot(WHEEL_SIZE, TRACK_SIZE, Motor.C, Motor.A, false);
	
	Random randomNum = new Random();
	
	pilot.setTravelSpeed(MOVE_SPEED); // wheel circumference / sec
	pilot.setRotateSpeed(TURN_SPEED); // rotations / sec
	
	LCD.clear();
	LCD.drawString("FWD  SPEED:" + MOVE_SPEED,0,0);
	LCD.drawString("TURN SPEED:" + TURN_SPEED,0,1);
	LCD.drawString("WHEEL:" + WHEEL_SIZE + "cm",0,2);
	LCD.drawString("Press any button",0,4);
	
	Button.waitForAnyPress();

	while (true) {
	    
	    pilot.forward(); 
	    
	    while (sonar.getDistance() > MIN_DIST) {
	    	LCD.drawString("SONAR: " + sonar.getDistance(),0,6);
	    	Delay.msDelay(100);
	    }
		
	    pilot.travel(-BK_DIST);
	    if (randomNum.nextInt(2) == 0) {
	    	pilot.rotate(90,false);
	    } else {
	    	pilot.rotate(90,false);
	    }
	}
    }
}

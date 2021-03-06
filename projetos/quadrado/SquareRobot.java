import lejos.nxt.Motor;
import lejos.nxt.Button;
import lejos.util.Delay;
import lejos.nxt.LCD;


class SquareRobot {
    public static int SPEED_DEG = 360; // degrees/sec
    public static int CIRCUMFERENCE = 180; // mm
    public static int DISTANCE = 500; // mm
    public static int TURN_DIST_DELAY = 500; // ms
    public static int SPEED_SEC =  CIRCUMFERENCE*SPEED_DEG/360; // mm/s
    public static int DELAY_FORWARD = 1000*DISTANCE/SPEED_SEC; // in ms
    
    public void moveForward() {
	Motor.A.forward();
	Motor.C.forward();	    
	// Wait until computed distance has been reached
	Delay.msDelay(DELAY_FORWARD);
	Motor.A.stop(true);
	Motor.C.stop();
    }

    public void turnLeft() {
	Motor.A.forward();
	Motor.C.backward();
	Delay.msDelay(TURN_DIST_DELAY);
	Motor.A.stop(true);
	Motor.C.stop();
    }

    public void turnRight() {
	Motor.A.backward();
	Motor.C.forward();
	Delay.msDelay(TURN_DIST_DELAY);
	Motor.A.stop(true);
	Motor.C.stop();
    }

    public static void main (String[] args){

	SquareRobot robot = new SquareRobot();
	
	Motor.A.setSpeed(SPEED_DEG); // Left motor
	Motor.C.setSpeed(SPEED_DEG); // Right motor
	
	while (true) {
	    LCD.clear();
	    LCD.drawString("SPEED:" + SPEED_SEC + "mm/s",0,0);
	    LCD.drawString("WHEEL:" + CIRCUMFERENCE + "mm",0,1);
	    LCD.drawString("DELAY:" + DELAY_FORWARD + "ms",0,2);
	    LCD.drawString("Press any <- or ->...",0,4);
	    Button.waitForAnyPress();
	    if (Button.LEFT.isPressed()) {
		LCD.clear();
		LCD.drawString("RIGHT ->", 0, 0);		
		for (int i=0; i < 4; i++) {		
		    robot.moveForward();
		    robot.turnRight();
		}
	    } else if (Button.RIGHT.isPressed()) {
		LCD.clear();
		LCD.drawString("LEFT <-", 0, 0);		
		for (int i=0; i < 4; i++) {		
		    robot.moveForward();
		    robot.turnLeft();
		}
	    }
	 }
    }
}

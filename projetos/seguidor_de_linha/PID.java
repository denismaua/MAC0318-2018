// Usando Robo 10
// Assume que robo está 'fora' da linha

// PID Controller
import lejos.nxt.*;

public class PID {

    public static int BASEPOWER = 40; // speed
    public static int TARGET = 45; // Light measurement on line edge
    public static int MAXERROR = 30; // trim error at this value to improve stability 
    public static int KP = 2; // error proportional power constant 
    public static int KI = 1; // error integrative power constant    
    public static int KD = -10; // error derivative power constant    
    
    public static void main(String[] args) {

	int control = 0;
	int error = 0; int prev_error = 0; int sum_error = 0;
        LightSensor light = new LightSensor(SensorPort.S3);
	
        NXTMotor mRight = new NXTMotor(MotorPort.B);
        NXTMotor mLeft = new NXTMotor(MotorPort.C);

	LCD.clear();
	LCD.drawString("BASE PWR :" + BASEPOWER,0,0);
	LCD.drawString("KP:" + KP,0,1);
	LCD.drawString("KI:" + KI,0,2);
	LCD.drawString("KD:" + KD,0,3);
	LCD.drawString("TARGET:" + TARGET,0,4);
	LCD.drawString("Place Robot on",0,5);	
	LCD.drawString(" line edge and",0,6);
	LCD.drawString("   press ENTER",0,7);	
	// Manual Calibration
	while (!Button.ENTER.isDown()) {
	    LCD.drawString("TARGET: " + light.getLightValue(),0,4);	    
	    TARGET = light.getLightValue();
	}
	TARGET = 45;
	LCD.clear();
	
        while (true){
	    // measure light minus expected measurement on line edge
	    prev_error = error;
	    error = light.getLightValue() - TARGET;
	    if (error > MAXERROR) {
	    	error = MAXERROR;
	    }
	    sum_error += error;
	    if (sum_error > MAXERROR) {
		    sum_error = MAXERROR;
	    }
	    control = KP*error + KI*sum_error/100 + KD*(error-prev_error)/100;
	    mLeft.setPower(BASEPOWER - control);
	    mRight.setPower(BASEPOWER + control);
        }
    }
}


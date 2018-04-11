// Usando Robo 10
// Assume que robo está 'fora' da linha

// Error Proportional Controller
import lejos.nxt.*;

public class Prop {

    public static int BASEPOWER = 30; // speed
    public static int THRESHOLD = 45; // Light measurement on line edge
    public static int MAXERROR = 10; // trim error at this value to improve stability 
    public static int KP = 3; // error proportional power constant    
    
    public static void main(String[] args) {

	int control = 0;
	int error = 0;
        LightSensor light = new LightSensor(SensorPort.S3);
	
        NXTMotor mRight = new NXTMotor(MotorPort.B);
        NXTMotor mLeft = new NXTMotor(MotorPort.C);

	LCD.clear();
	LCD.drawString("BASE PWR :" + BASEPOWER,0,0);
	LCD.drawString("THRESHOLD:" + THRESHOLD,0,1);
	LCD.drawString("Place Robot on",0,4);	
	LCD.drawString(" line edge and",0,5);
	LCD.drawString("   press ENTER",0,5);	
	// Manual Calibration
	while (!Button.ENTER.isDown()) {
	    LCD.drawString("LIGHT: " + light.getLightValue(),0,2);	    
	    THRESHOLD = light.getLightValue();
	}
	
	LCD.clear();
	
        while (true){
	    // measure light minus expected measurement on line edge
	    error = light.getLightValue() - THRESHOLD;
	    if (error > MAXERROR) {
		error = MAXERROR;
	    }
	    
	    mLeft.setPower(BASEPOWER - KP*error);
	    mRight.setPower(BASEPOWER + KP*error); 

        }
    }
}


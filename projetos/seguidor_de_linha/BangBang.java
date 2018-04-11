// Usando Robo 10
// Assume que robo está 'fora' da linha

import lejos.nxt.*;

public class BangBang {

    public static int BASEPOWER = 30; // speed
    public static int THRESHOLD = 45; // threshold for deciding control
    public static int KP = 10; // error proportional power constant
    
    public static void main(String[] args) {

	int control = 0;
	
        LightSensor light = new LightSensor(SensorPort.S3);
	
        NXTMotor mRight = new NXTMotor(MotorPort.B);
        NXTMotor mLeft = new NXTMotor(MotorPort.C);

	LCD.clear();
	LCD.drawString("BASE PWR :" + BASEPOWER,0,0);
	LCD.drawString("THRESHOLD:" + THRESHOLD,0,1);
	LCD.drawString("press ENTER",0,4);	

	while (!Button.ENTER.isDown()) {
	    LCD.drawString("LIGHT: " + light.getLightValue(),0,2);	    
	}

	LCD.clear();
	
        while (true){

	    mLeft.setPower(BASEPOWER - control);
	    mRight.setPower(BASEPOWER + control); 

            if (light.getLightValue() > THRESHOLD) { // if we are on the outside (white part)
		control = KP;
            } else { // if we are on the inside (black part)
		control = -KP;
	    }
        }
    }
}


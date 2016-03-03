package helloWorld1;

import lejos.nxt.Button;
import lejos.nxt.Sound;
import lejos.nxt.ButtonListener;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.RegulatedMotorListener;

public class HelloWorld implements ButtonListener {
	/**
	 * @param args
	 */
    private static TouchSensor touchSensor;
    private static UltrasonicSensor ultraSensor;
    
    
	public static void main(String[] args) throws Exception {
		initialize();
		while(true)
		{
				//exercise1();
			    exercise2();
			
		}
				
		
			
		}
	
	public static void initialize()
	{
		HelloWorld listener = new HelloWorld();
		Button.ESCAPE.addButtonListener(listener);
		float speed1 = 100;
		float speed2 = 200;
		Motor.B.setSpeed(speed1);
		Motor.C.setSpeed(speed2);
		touchSensor = new TouchSensor(SensorPort.S1);
		ultraSensor = new UltrasonicSensor(SensorPort.S4);
		
	}
	
	public static void exercise2() throws InterruptedException
	{
			if(checkSensorMinLeftRange())
			{
				goRight();
			}
			else if(checkSensorMaxLeftRange())
			{
				goLeft();
			}
			else if(checkSensorOutOfRange())
			{
				spinLeft(90);
			}
			else if(checkIfHit() || checkSensorRange())
			{
				stop();
				turn();
			}
			
			forward();
	
				
		
	}
	
	public static void goRight() throws InterruptedException
	{
		Motor.C.setSpeed(Motor.C.getSpeed()-50);
		Thread.sleep(2000);
		Motor.C.setSpeed(Motor.C.getSpeed()+50);
	}
	
	public static void goLeft() throws InterruptedException
	{
		Motor.B.setSpeed(Motor.B.getSpeed()-50);
		Thread.sleep(2000);
		Motor.B.setSpeed(Motor.B.getSpeed()+50);
	}
	
	

	public static void exercise1() throws InterruptedException
	{
		if(checkIfHit() || checkSensorRange())
		{
			
			stop();
			turn();
																	
		}
		else
		{
			forward();
			
		}
	}
	
	public static void turn() throws InterruptedException
	{
		turnHead(-90);
		float leftDistance = ultraSensor.getRange();
		turnHead(180);
		float rightDistance = ultraSensor.getRange();
		turnHead(-90);
		if(leftDistance > rightDistance)
		{
			spinRight(90);
		}
		else
		{
			spinLeft(90);
		}
		
	}
	
	
	private static void turnHead(int degrees) {
		Motor.A.setSpeed(100);
		Motor.A.rotate(degrees);
		
		
	}

	public static boolean checkIfHit()	{
		
		if(touchSensor.isPressed())
		{
			lejos.nxt.Sound.beep();
			return true;
		}
		return false;
	}	
	
	public static boolean checkSensorMinLeftRange()	{
		
		Motor.A.rotate(-90);
	
		if(ultraSensor.getRange() < 30)
		{	
			Motor.A.rotate(90);
			return true;
		}
		Motor.A.rotate(90);
		return false;
	}
	
	public static boolean checkSensorMaxLeftRange()	{
		
		Motor.A.rotate(-90);
		
		if(ultraSensor.getRange() > 35)
		{	
			Motor.A.rotate(90);
			return true;
		}
		Motor.A.rotate(90);
		return false;
	}
	
	public static boolean checkSensorOutOfRange()	{
		
		Motor.A.rotate(-90);
		
		if(ultraSensor.getRange() > 100)
		{	
			Motor.A.rotate(90);
			return true;
		}
		Motor.A.rotate(90);
		return false;
	}
			
	public static boolean checkSensorRange()	{
		
		if(ultraSensor.getRange() < 30)
		{
			return true;
		}
		return false;
	}
	
	
	public static void forward(){
		Motor.B.forward();
		Motor.C.forward();

	}
	
	public static void backward(){
		Motor.B.backward();
		Motor.C.backward();

	}
		

	public static void stop() throws InterruptedException
	{
		Motor.B.stop();
		Motor.C.stop();
		Thread.sleep(1000);
	}
	
	

	private static void spinLeft(int degrees) throws InterruptedException {
		
		Motor.B.backward();
		Motor.C.backward();
		Thread.sleep(500);
		
		Motor.B.forward();
		Motor.C.backward();
		Thread.sleep(degrees*15);
	}
	
	private static void spinRight(int degrees) throws InterruptedException {
		
		Motor.B.backward();
		Motor.C.backward();
		Thread.sleep(500);
		
		Motor.C.forward();
		Motor.B.backward();
		Thread.sleep(degrees*15);
	}

	public void buttonPressed(Button b) {
	}

	public void buttonReleased(Button b) {
		System.exit(0);
	}



}

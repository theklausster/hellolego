package helloWorld1;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;



public class Sensor implements Runnable{

	public boolean isTouched = false;
	private TouchSensor ts;
	
	public Sensor(TouchSensor ts)
	{
		this.ts = ts;
	}
	
	@Override
	public void run() {
		
		while(true)
		{
			if(ts.isPressed())
			{
				System.out.print(isTouched);
				isTouched = true;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	

}

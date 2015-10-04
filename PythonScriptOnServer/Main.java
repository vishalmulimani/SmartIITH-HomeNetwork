package Sensor;

import java.io.IOException;

public class Main {
	Thread MobileServerThread ;
	Thread TempSensorThread;
	Thread LightSensorThread;
	Thread DistanceSensorThread;
	Thread GasSensorThread;
	Thread TempUdpThread;
	Thread MotionThread;


	public Main(int mobileport,int tempport,int lightport,
			int distport,int gasport,int tempudp,int motionport) {
		try{

			MobileServerThread =  new MobileServer(mobileport); 
			MobileServerThread.start();
			/*
			TempSensorThread = new TempSensor(tempport);
			TempSensorThread.start();
			
			LightSensorThread = new LightSensor(lightport);
			LightSensorThread.start();
			
			DistanceSensorThread = new DistanceSensor(distport);
			DistanceSensorThread.start();
			
			GasSensorThread = new GasSensor(gasport);;
			GasSensorThread.start();
			*/
			TempUdpThread = new TempratureUdp(tempudp);
			TempUdpThread.start();
			
			MotionThread =new MotionUdp(motionport);
			MotionThread.start();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}


	public static void main(String [] args)
	{

		Main SmartApp = new Main(11000,11001,11002,11003,11004,11006,11005);
	}

}

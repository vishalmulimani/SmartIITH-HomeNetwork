package Sensor;
import java.net.*;
import java.io.*;

public class MotionUdp extends Thread
{
	private  DatagramSocket serverSocket;

	public MotionUdp(int port) throws IOException
	{
		serverSocket = new  DatagramSocket(port);
		serverSocket.setSoTimeout(1000000);
	}

	public void run()
	{
		while(true)
		{
			String rec=null;
			DatagramPacket receivePacket=null;
			try{
				while(true)
				{
					System.out.println("Waiting for Motion sensor to Connect on port ");
					byte[] receiveData = new byte[2048];
					receivePacket = new DatagramPacket(receiveData, receiveData.length);
					System.out.println(":Waitig for data from sensor ");

					serverSocket.receive(receivePacket); 
					rec = new String( receivePacket.getData());
					if(rec!="STOP"){
						System.out.println("Receved "+ rec);
						break;
					}
					System.out.println(rec);

				}  
			}catch(SocketTimeoutException s)
			{
				System.out.println("Socket timed out!");
				break;
			}catch(IOException e)
			{
				e.printStackTrace();
				break;
			}
		}
	}

}

package Sensor;
import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
public class TempratureUdp extends Thread
{
	private  DatagramSocket serverSocket;
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/sensor";//3306

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "Prakash";

	public TempratureUdp(int port) throws IOException
	{
		serverSocket = new  DatagramSocket(port);
		serverSocket.setSoTimeout(1000000);
	}

	public void run()
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		while(true)
		{
			String rec=null;
			DatagramPacket receivePacket=null;
			System.out.println("Waiting for Sensor to Connect on port 11006");

			try{
				int temp=70, lpg=0;
				
				
				while(true)
				{
					byte[] receiveData = new byte[2048];
					receivePacket = new DatagramPacket(receiveData, receiveData.length);
					System.out.println(":Waiting for data from sensor ");
					//distance,light-intensity ,temp,co,methane
					serverSocket.receive(receivePacket); 
					rec = new String(receivePacket.getData());
					if(rec=="STOP"){
						System.out.println("Receved STOP"+ rec);
						break;
					}
					rec=rec+"";
					List<String> elementList =  Arrays.asList(rec.split(","));
					//List<Integer>eleint = elementList.Select(s=>int.Parse)).ToList(); 
					System.out.println("Receved"+ rec);
					if(elementList.size()==5) {
						/*String sql = "INSERT INTO sensor VALUES("+ Integer.parseInt(elementList.get(0) )+","
					+Integer.parseInt(elementList.get(1) )+","+Integer.parseInt(elementList.get(2))
					+","+Integer.parseInt(elementList.get(3))+","+Integer.parseInt(elementList.get(4))+")" ;*/
						String sql = "INSERT INTO sensor (distance,light,temprature,co,methane) VALUES("+ Integer.parseInt(elementList.get(0) )+","
								+Integer.parseInt(elementList.get(1) )+","+Integer.parseInt(elementList.get(2))
								+","+Integer.parseInt(elementList.get(3))+","+373+")";//+Integer.parseInt(elementList.get(4))+")" ;
						System.out.println(sql);
						if(temp >400)
						{
							String[] command3= new String[]{"expect","/home/prakash/light.py"};
							Events.executeCommand(command3);
							String[] command4= new String[]{"expect","/home/prakash/buzzer.py"};
							Events.executeCommand(command4);
							
						}
						try {
							stmt.executeUpdate(sql);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} 
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

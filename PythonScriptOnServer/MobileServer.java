package Sensor;

import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.io.*;

public class MobileServer extends Thread
{
   private ServerSocket serverSocket;
   
   public MobileServer(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(1000000);
   }
public void parse(String str){
	List<String> elephantList =  Arrays.asList(str.split(","));
	int i=0;
	for (String temp : elephantList ) {
		System.out.println(i+"  "+temp);
	}
	//95562d08-ce69-4533-9911-d35ae0330c15,Ayush,ayush@ghh.gg,Smart Temperate,true,true,Low,OpenWrt 
	System.out.println("smart setting");
	if(elephantList.get(3).equalsIgnoreCase("smart"))
	{
		String[] command3= new String[]{"expect","/home/prakash/light.py"};
		Events.executeCommand(command3);
		
	}
}
   public void run()
   {
      while(true)
      {
    	  String rec=null;
         try
         {
            System.out.println("Waiting for Mobile client on port " +
            serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            PrintStream cout=new PrintStream(server.getOutputStream());
            System.out.println("Receving user the profile :=");  
            BufferedReader cin=new BufferedReader(new InputStreamReader(server.getInputStream()));
            while(rec!="null"){
            System.out.println("Receving from temprature sensor");  
            rec=cin.readLine();
            System.out.println(rec);
            if(rec!=null)parse(rec);
            }
            server.close();
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

package Sensor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Events {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//read temp from file in temp
		int temp=70, lpg=0;
		if(temp > 60)
		{
			Led();
		}
//		if(lpg > 2)
//		{
//			Buzzer();
//			Fan();
//		}
		
      
	}
	
	private static  void Led()
	{
		String[] command3= new String[]{"python","hi.py"};
        executeCommand(command3);
        String [] cmd = new String[]{"ssh pi@192.168.1.193"};
        executeCommand(cmd);
        System.out.println("exeeeeeeeeeeeeee");
	}
	
//	private static void Fan()
//	{
//		String[] command3= new String[]{"python","Fan.py"};
//        executeCommand(command3);
//	}
//
//	private static void Buzzer()
//	{
//		String[] command3= new String[]{"python","Buzzer.py"};
//        executeCommand(command3);
//	}
	
	 public static void  executeCommand(String[] command)  
	  {
		String s = null;
	        try {
	             
	        // run the Unix "XXXXXXX" command
	            // using the Runtime exec method:
	             //String[] command= new String[]{"curl","-s","http://localhost:8080/wm/topology/links/json",">","/Users/pragati/Desktop/topology/Output.txt"};
	            Process p = Runtime.getRuntime().exec(command);
	             
	            BufferedReader stdInput = new BufferedReader(new
	                 InputStreamReader(p.getInputStream()));
	 
	            BufferedReader stdError = new BufferedReader(new
	                 InputStreamReader(p.getErrorStream()));
	 
	            // read the output from the command
	            System.out.println("Here is the standard output of the command:\n");
	            while ((s = stdInput.readLine()) != null) {
	                System.out.println(s);
	            }
	             
	            // read any errors from the attempted command
	            System.out.println("Here is the standard error of the command (if any):\n");
	            while ((s = stdError.readLine()) != null) {
	                System.out.println(s);
	            }
	             
	            //System.exit(0);
	        }
	        catch (IOException e) {
	            System.out.println("exception happened - here's what I know: ");
	            e.printStackTrace();
	            System.exit(-1);
	        }
		 
	 
		}

}

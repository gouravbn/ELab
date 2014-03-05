package Controller;
import javax.swing.SwingUtilities;

import View.*;
import Model.*;
public class Main {

	public static Resistor rs[] = new Resistor[15*7];
	public static int rspointer = 0;
	public static DCsource dc = new DCsource();
	public static int earthingx,earthingy;
	public static void main(String args[])
	{
		for(int i = 0; i<rs.length;i++)
			rs[i] = new Resistor();
		earthingx = -1;
		earthingy = -1;
		
		// Loading view of the system.
		SwingUtilities.invokeLater(new Runnable(){
		     public void run(){
		     openWindow obj = new openWindow();
		     
		     }
		});
		
		
		
	}
	
	
}

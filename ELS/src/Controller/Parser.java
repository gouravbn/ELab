package Controller;

import java.awt.Color;

import java.io.BufferedReader;

import java.io.FileReader;


import javax.swing.JButton;
import javax.swing.JPanel;

import Model.DCsource;
import Model.Resistor;


public class Parser {

	JPanel jpanel;
	@SuppressWarnings("resource")
	public Parser(String lp,JPanel jp,JButton junction[][]) throws Exception
	{
        jpanel = jp;
    	BufferedReader br = null;
    	 
		String sCurrentLine;
 		br = new BufferedReader(new FileReader(lp));
 		if(!lp.endsWith(".els"))
 			throw new Exception();
 		
 		// Saving new environment :
 		
 		for(int i = 0; i<Main.rs.length;i++)
			Main.rs[i] = new Resistor();
		Main.earthingx = -1;
		Main.earthingy = -1;
 		Main.dc = new DCsource();
 		Main.rspointer = 0;
 		
 		jp.repaint();
		jp.validate();
 		jp.removeAll();
 		
 		for(int i = 0;i<15;i++)
	    {
	      for(int j = 0;j<15;j++)
	      {	  
	    	 junction[i][j].setBounds(40+i*43,30+j*43,5,5);
	         junction[i][j].setBackground(Color.GRAY);
	         jp.add(junction[i][j]);
	         
	      }   
	    }
 		
 		
 		//  Parse :
 		
 		while ((sCurrentLine = br.readLine()) != null) 
 		{
 			if ( sCurrentLine == "\n" || sCurrentLine.startsWith("$$") || sCurrentLine.contains("cid") || sCurrentLine.contains("Circuit") || sCurrentLine.contains("Project"))
 				continue;
 			
 			
 			if(sCurrentLine.contains("Resistor"))
 			{
 				try{
 				  sCurrentLine = br.readLine();
 				  int val = sCurrentLine.indexOf(">");
 				  val = val+2;
 				  int final_val = sCurrentLine.indexOf(" </");
 				  double resistence = Double.parseDouble(sCurrentLine.substring(val, final_val));
 				 
 				  Main.rs[Main.rspointer].resistance = resistence;
 				  sCurrentLine = br.readLine();
 				  val = sCurrentLine.indexOf(">");
				  val = val+2;
				  final_val = sCurrentLine.indexOf(" </");
				  Main.rs[Main.rspointer].x1 = Integer.parseInt(sCurrentLine.substring(val,final_val));
				  sCurrentLine = br.readLine();
 				  val = sCurrentLine.indexOf(">");
				  val = val+2;
				  final_val = sCurrentLine.indexOf(" </");
				  Main.rs[Main.rspointer].y1 = Integer.parseInt(sCurrentLine.substring(val,final_val));
				  sCurrentLine = br.readLine();
 				  val = sCurrentLine.indexOf(">");
				  val = val+2;
				  final_val = sCurrentLine.indexOf(" </");
				  Main.rs[Main.rspointer].x2 = Integer.parseInt(sCurrentLine.substring(val,final_val));
				  sCurrentLine = br.readLine();
 				  val = sCurrentLine.indexOf(">");
				  val = val+2;
				  final_val = sCurrentLine.indexOf(" </");
				  Main.rs[Main.rspointer].y2 = Integer.parseInt(sCurrentLine.substring(val,final_val));
				  Main.rspointer++;
                  sCurrentLine = br.readLine();    				
 				}
 				catch(Exception e)
 				{
 					throw new Exception();
 				}
 			}
 			else if(sCurrentLine.contains("Wire"))
 			{
 				try{
 				  sCurrentLine = br.readLine();
 				  int val,final_val;
 				  val = sCurrentLine.indexOf(">");
				  val = val+2;
				  final_val = sCurrentLine.indexOf(" </");
				  Main.rs[Main.rspointer].resistance = 0.0;
				  Main.rs[Main.rspointer].x1 = Integer.parseInt(sCurrentLine.substring(val,final_val));
				  sCurrentLine = br.readLine();
 				  val = sCurrentLine.indexOf(">");
				  val = val+2;
				  final_val = sCurrentLine.indexOf(" </");
				  Main.rs[Main.rspointer].y1 = Integer.parseInt(sCurrentLine.substring(val,final_val));
				  sCurrentLine = br.readLine();
 				  val = sCurrentLine.indexOf(">");
				  val = val+2;
				  final_val = sCurrentLine.indexOf(" </");
				  Main.rs[Main.rspointer].x2 = Integer.parseInt(sCurrentLine.substring(val,final_val));
				  sCurrentLine = br.readLine();
 				  val = sCurrentLine.indexOf(">");
				  val = val+2;
				  final_val = sCurrentLine.indexOf(" </");
				  Main.rs[Main.rspointer].y2 = Integer.parseInt(sCurrentLine.substring(val,final_val));
				  sCurrentLine = br.readLine();  
 				  Main.rspointer++;
 				}
 				catch(Exception e)
 				{
 					throw new Exception();
 				}
 			}
 			else if(sCurrentLine.contains("DC Source"))
 			{
 				try{
 				  sCurrentLine = br.readLine();
 				  int val = sCurrentLine.indexOf(">");
 				  val = val+2;
 				  int final_val = sCurrentLine.indexOf(" </");
 				  double resistence = Double.parseDouble(sCurrentLine.substring(val, final_val));
 				  Main.dc.voltage = resistence;
 				  sCurrentLine = br.readLine();
 				  val = sCurrentLine.indexOf(">");
				  val = val+2;
				  final_val = sCurrentLine.indexOf(" </");
				  Main.dc.x1 = Integer.parseInt(sCurrentLine.substring(val,final_val));
				  sCurrentLine = br.readLine();
 				  val = sCurrentLine.indexOf(">");
				  val = val+2;
				  final_val = sCurrentLine.indexOf(" </");
				  Main.dc.y1 = Integer.parseInt(sCurrentLine.substring(val,final_val));
				  sCurrentLine = br.readLine();
 				  val = sCurrentLine.indexOf(">");
				  val = val+2;
				  final_val = sCurrentLine.indexOf(" </");
				  Main.dc.x2 = Integer.parseInt(sCurrentLine.substring(val,final_val));
				  sCurrentLine = br.readLine();
 				  val = sCurrentLine.indexOf(">");
				  val = val+2;
				  final_val = sCurrentLine.indexOf(" </");
				  Main.dc.y2 = Integer.parseInt(sCurrentLine.substring(val,final_val));
				  sCurrentLine = br.readLine();  
 				  Main.rspointer++;
 				}
 				catch(Exception e)
 				{
 					throw new Exception();
 				}
 			}
 			else if(sCurrentLine.contains("Earthing"))
 			{
 				try{
 				  int val,final_val;
 				  sCurrentLine = br.readLine();
 				  val = sCurrentLine.indexOf(">");
				  val = val+2;
				  final_val = sCurrentLine.indexOf(" </");
				  Main.earthingx = Integer.parseInt(sCurrentLine.substring(val,final_val));
				  sCurrentLine = br.readLine();
 				  val = sCurrentLine.indexOf(">");
				  val = val+2;
				  final_val = sCurrentLine.indexOf(" </");
				  Main.earthingy = Integer.parseInt(sCurrentLine.substring(val,final_val));
				  sCurrentLine = br.readLine();  
 				}
 				catch(Exception e)
 				{
 					throw new Exception();
 				}
 			}
 			
 		}
 		
 		
 		////////////////
 		
 		
	}

	
}

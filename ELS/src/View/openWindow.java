package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.ToolTipManager;

import Controller.DCsourceHandler;
import Controller.LoadingRoutine;
import Controller.Main;
import Controller.SavingRoutine;
import Controller.resistorHandler;
import Controller.simplifier;
import Controller.solver;
import Model.DCsource;
import Model.Junction;
import Model.Resistor;


public class openWindow implements ActionListener {

	  int height,width;
	  int activex[] = new int[2];
	  int activey[] = new int[2];
	  public static JButton junction[][] = new JButton[15][15];
	  boolean junction_bool[][] = new boolean[15][15];
	  int junction_test;
	  JPanel jp2;
	  drawResistor dR;
	  drawWire dW;
	  drawDc dD;
	  drawGround dG;
	  JButton R,Wire,dc,Earthing;
	  JFrame obj;
	  JPanel jp13;
	  JButton save,load,delete,solve;
	  JLabel labeloperation;
	  drawCoordinates dC;
	  
	  static drawResistor del;
	  public openWindow()
	  {
		// Workspace :
		  
		activex[0] = activex[1] = -1;
		activey[0] = activey[1] = -1;
	    obj = new JFrame("Electrical Laboratory Simulation");                          // Defining Frame and initialising its name.
	    height = 700;
	    width = 900;
	    obj.setSize(width,height);                                        // Setting Size
	    obj.setLocation(200, 50);
	    obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Terminate all operations when cross button is pressed.
	    obj.setVisible(true);            // Make Frame visible.
	    obj.setLayout(null);
	    
	    
	    
	    
	    JPanel jp1 = new JPanel();                // Creating new Panel.
	    jp1.setBounds(0,0,(int)(0.25*width),(int)(0.95*height));
	    jp1.setBackground(Color.GRAY);
	    jp1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
	    obj.add(jp1);
	    jp2 = new JPanel();
	    jp2.setBounds((int)(0.25*width),0,(int)(0.748*width),(int)(0.95*height));
	    jp2.setBackground(Color.WHITE);
	    jp2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
	    obj.add(jp2);
	    obj.setResizable(false);
	    jp1.setLayout(null);
	    jp2.setLayout(null);
	    
	    
	    // Components 
	    
	    for(int i = 0;i<15;i++)
	    {
	      for(int j = 0;j<15;j++)
	      {	  
	    	 junction[i][j] = new JButton(); 
	    	 junction_bool[i][j] = false;
	         junction[i][j].setBounds(40+i*43,30+j*43,5,5);
	         junction[i][j].setBackground(Color.GRAY);
	         junction[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	         junction[i][j].addActionListener(this);
	         jp2.add(junction[i][j]);
	      }   
	    }
	    
	    dC = new drawCoordinates();
	    jp2.add(dC);
	    dC.setBounds(0,0,800,800);
	    junction_test = 0;
	    
	    // Left Panel :
	    
	    JPanel jp11 = new JPanel();
	    jp11.setLayout(null);
	    jp11.setBounds(0,0,(int)(0.25*width),150);
	    jp11.setBackground(Color.LIGHT_GRAY);
	    jp11.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
	    JLabel source = new JLabel();
	    source.setText("Source");
	    source.setForeground(Color.BLACK);
	    Font f1 = new Font("Arial",Font.BOLD,20);
	    source.setFont(f1);
	    source.setBounds(80, 5, 100, 50);
	    
	    JButton Ac = new JButton();
	    Ac.setBounds(20,60,90,50);
	    ImageIcon imgac = new ImageIcon(openWindow.class.getResource("/images/ac1.jpg"));
        Ac.setIcon(imgac);
        Ac.setToolTipText("AC");
	    
	    dc = new JButton();
	    dc.setBounds(120,60, 90,50);
	    ImageIcon imgdc = new ImageIcon(openWindow.class.getResource("/images/dc1.jpg"));
        dc.setIcon(imgdc);
        dc.setToolTipText("DC");
	    jp11.add(source);
	    jp11.add(Ac);
	    jp11.add(dc);
	    dc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dc.addActionListener(this);
	  
	    jp1.add(jp11);
	 
	    JPanel jp12 = new JPanel();
	    jp12.setLayout(null);
	    jp12.setBounds(0,150,(int)(0.25*width),360);
	    jp12.setBackground(Color.LIGHT_GRAY);
	    jp12.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
	    JLabel component = new JLabel();
	    component.setText("Components");
	    component.setForeground(Color.BLACK);
	    component.setFont(f1);
	    component.setBounds(40, 5, 150, 50);
	    
	    R = new JButton();
	    R.setBounds(50,60,120,50);
	    ImageIcon img = new ImageIcon(openWindow.class.getResource("/images/resistors1.jpg"));
        R.setIcon(img);
        R.setToolTipText("Resistor");
	    R.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        R.addActionListener(this);
	    
	    JButton L = new JButton();
	    L.setBounds(50,120, 120,50 );
	    ImageIcon imgi = new ImageIcon(openWindow.class.getResource("/images/induct1.jpg"));
        L.setIcon(imgi);
        L.setToolTipText("Inductor");
	    jp12.add(component);
	    
	    JButton C = new JButton();
	    C.setBounds(50,180,120,50);
	    ImageIcon imgc = new ImageIcon(openWindow.class.getResource("/images/capap1.jpg"));
        C.setIcon(imgc);
        C.setToolTipText("Capacitor");
	    
	    Wire = new JButton();
	    Wire.setBounds(50,240,120,50);
	    ImageIcon imgw = new ImageIcon(openWindow.class.getResource("/images/wire1.jpg"));
        Wire.setIcon(imgw);
        Wire.setToolTipText("Wire");
	    Wire.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Wire.addActionListener(this);
	    
        Earthing = new JButton();
        Earthing.setBounds(50,300,120,50);
	    ImageIcon imge = new ImageIcon(openWindow.class.getResource("/images/eearth1.jpg"));
	    Earthing.setIcon(imge);
	    Earthing.setToolTipText("Earthing");
	    Earthing.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    Earthing.addActionListener(this);
        
        jp12.add(Wire);
        jp12.add(Earthing);
	    jp12.add(R);
	    jp12.add(L);
	    jp12.add(C);
	    jp1.add(jp12);
	   
	    jp13 = new JPanel();
	    jp13.setLayout(null);
	    jp13.setBounds(0,500,(int)(0.25*width),360);
	    jp13.setBackground(Color.LIGHT_GRAY);
	    jp13.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
	    labeloperation = new JLabel();
	    labeloperation.setText("Operations");
	    labeloperation.setForeground(Color.BLACK);
	    labeloperation.setFont(f1);
	    labeloperation.setBounds(40, 5, 150, 50);
	    
	    
        save = new JButton();
        save.setBounds(30,125,70,25);
        save.setToolTipText("Save");
        save.setText("Save");
        save.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        save.addActionListener(this);
	    
        load = new JButton();
        load.setBounds(120,125,70,25);
        load.setToolTipText("Load");
        load.setText("Load");
        load.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        load.addActionListener(this);
	    
        delete = new JButton();
        delete.setBounds(20,95,190,25);
        delete.setToolTipText("Delete");
        delete.setText("Delete Component");
        delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        delete.addActionListener(this);
        
        solve = new JButton();
        solve.setBounds(60,43,100,50);
        solve.setToolTipText("Solve");
        solve.setText("Solve");
        solve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        solve.addActionListener(this);
        
	    jp13.add(labeloperation);
	    jp13.add(save);
	    jp13.add(load);
	    jp13.add(delete);
	    jp13.add(solve);
	    jp1.add(jp13);
	      
	    
	    
	  }
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
		if(ae.getSource() == solve)
		{
			new simplifier();
			solver sol = new solver();
			JPanel jpp = new JPanel(new GridLayout(11,10,2,2));
			JLabel jlb = new JLabel();
			jlb.setText("  Junction (a,b)        |         Voltage(volt)");
			jpp.add(jlb);
			jpp.add(Box.createRigidArea(new Dimension(5,0)));
		    jpp.add(new JSeparator());
		    Junction junc[] = sol.answer;
		    JLabel jval[] = new JLabel[junc.length];
		    for(int i = 0;i<junc.length;i++)
		    {
		    	jval[i] = new JLabel();
				jval[i].setText(i+1+") "+"   ("+junc[i].y+","+junc[i].x+")                  |         "+junc[i].voltage);
				jpp.add(jval[i]);
		    }
			int val = JOptionPane.showConfirmDialog(obj,jpp,"Circuit Analysis : ",
		               JOptionPane.OK_CANCEL_OPTION,
		               JOptionPane.PLAIN_MESSAGE);
			
		}
		if(ae.getSource() == load)
		{
			try {
				new LoadingRoutine(jp2,junction);
				junction_test = 0;
				dC = new drawCoordinates();
			    jp2.add(dC);
			    dC.setBounds(0,0,800,800);
			    activex[0] = activex[1] = -1;
				activey[0] = activey[1] = -1;
				
				
				// Earthing :
				
				
				if(Main.earthingx !=-1 && Main.earthingy!=-1)
				{
				   dG = new drawGround(40+Main.earthingx*43,30+Main.earthingy*43);	
				   junction_bool[Main.earthingx][Main.earthingy] = false;
				   junction[Main.earthingx][Main.earthingy].setSize(7,7);
				   junction[Main.earthingx][Main.earthingy].setBackground(Color.green);
			       jp2.add(dG);
			       dG.setBounds(0,0,800,800);
				}
				
				
				// DC :
				
				if(Main.dc.x1 !=-1 && Main.dc.y1 != -1  && Main.dc.x2 != -1 && Main.dc.y2 != -1)
				{
					double value = Main.dc.voltage;
					if(Main.dc.rtol)
					
				    	 dD  = new drawDc(40+Main.dc.x2*43,30+Main.dc.y2*43,40+Main.dc.x1*43,30+Main.dc.y1*43,value);
				
				    else
				    
				    	 dD  = new drawDc(40+Main.dc.x1*43,30+Main.dc.y1*43,40+Main.dc.x2*43,30+Main.dc.y2*43,value);
				    
				  jp2.add(dD);
				  dD.setBounds(0,0,800,800);
				  junction_bool[Main.dc.x1][Main.dc.y1] = false;
				  junction[Main.dc.x1][Main.dc.y1].setSize(7,7);
				  junction[Main.dc.x1][Main.dc.y1].setBackground(Color.green);
			      
				  
			      junction_bool[Main.dc.x2][Main.dc.y2] = false;
				  junction[Main.dc.x2][Main.dc.y2].setSize(7,7);
				  junction[Main.dc.x2][Main.dc.y2].setBackground(Color.green);
				}
				
				// Resistor :
				
				
			   for(int i = 0;i<Main.rs.length;i++)	
			   {	
				
				if(Main.rs[i].x1 !=-1 && Main.rs[i].y1 != -1  && Main.rs[i].x2 != -1 && Main.rs[i].y2 != -1 && Main.rs[i].resistance !=-1)  
				{	 
				 double value = Main.rs[i].resistance;
				  if(value == 0.0)
				 {
					 if(Main.rs[i].x1 > Main.rs[i].x2)
						 dW  = new drawWire(40+Main.rs[i].x2*43,30+Main.rs[i].y2*43,40+Main.rs[i].x1*43,30+Main.rs[i].y1*43);
					 else if(Main.rs[i].x1 == Main.rs[i].x2 && Main.rs[i].y1>Main.rs[i].y2)
				
						 dW  = new drawWire(40+Main.rs[i].x2*43,30+Main.rs[i].y2*43,40+Main.rs[i].x1*43,30+Main.rs[i].y1*43);
					
					 else 
					    dW  = new drawWire(40+Main.rs[i].x1*43,30+Main.rs[i].y1*43,40+Main.rs[i].x2*43,30+Main.rs[i].y2*43);
					  jp2.add(dW);
					  dW.setBounds(0,0,800,800);
				 }
				 else
				 {
					if(Main.rs[i].x1 > Main.rs[i].x2)
					  dR  = new drawResistor(40+Main.rs[i].x2*43,30+Main.rs[i].y2*43,40+Main.rs[i].x1*43,30+Main.rs[i].y1*43,value);
				   else if(Main.rs[i].x1 == Main.rs[i].x2 && Main.rs[i].y1>Main.rs[i].y2)
			
					 dR  = new drawResistor(40+Main.rs[i].x2*43,30+Main.rs[i].y2*43,40+Main.rs[i].x1*43,30+Main.rs[i].y1*43,value);
				
				   else 
				     dR  = new drawResistor(40+Main.rs[i].x1*43,30+Main.rs[i].y1*43,40+Main.rs[i].x2*43,30+Main.rs[i].y2*43,value);
				     jp2.add(dR);
				     dR.setBounds(0,0,800,800);
				 }
				  
				  junction_bool[Main.rs[i].x1][Main.rs[i].y1] = false;
				  junction[Main.rs[i].x1][Main.rs[i].y1].setSize(7,7);
				  junction[Main.rs[i].x1][Main.rs[i].y1].setBackground(Color.green);
			      
				  
			      junction_bool[Main.rs[i].x2][Main.rs[i].y2] = false;
				  junction[Main.rs[i].x2][Main.rs[i].y2].setSize(7,7);
				  junction[Main.rs[i].x2][Main.rs[i].y2].setBackground(Color.green);
			      
			      
				}
			   }
				
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				// Error Parsing File : 
				JOptionPane.showMessageDialog(obj,
					    "Cannot Parse given file.",
					    "Error while parsing",
					    JOptionPane.ERROR_MESSAGE);
				
			}
		}
		if(ae.getSource() == save)
		{
			try {
				new SavingRoutine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(ae.getSource() == Earthing)
		{
			if(junction_test >=1)
			{
				dG = new drawGround(40+activex[0]*43,30+activey[0]*43);
				Main.earthingx = activex[0];Main.earthingy = activey[0];
				
				junction_bool[activex[0]][activey[0]] = false;
				junction[activex[0]][activey[0]].setSize(7,7);
				junction[activex[0]][activey[0]].setBackground(Color.green);
				activex[0] = activex[1];
				activey[0] = activey[1];
			    junction_test--;
			    jp2.add(dG);
			    dG.setBounds(0,0,800,800);
			}
		}
		if(ae.getSource() == delete)
		{
			int flag = 0;
			if(junction_test == 1)
			{
			   if(Main.earthingx != -1 && Main.earthingy!=-1)
			   {
				   if(Main.earthingx == activex[0] && Main.earthingy == activey[0])
				   {
					   Main.earthingx = -1;
					   Main.earthingy = -1;
					   flag = 1;
				   }
			   }
				   
			}
			else if(junction_test == 2)
			{
				if((Main.dc.x1 == activex[0] && Main.dc.y1 == activey[0]  && Main.dc.x2 == activex[1]  && Main.dc.y2 == activey[1] ) || (Main.dc.x1 == activex[1]  && Main.dc.y1 == activey[1]  && Main.dc.x2 == activex[0]  && Main.dc.y2 == activey[0]))
				{
					Main.dc = new DCsource();
					flag = 1;
				}
				else
				{
					for(int i = 0;i<Main.rs.length;i++)
					{
						if((Main.rs[i].x1 == activex[0]  && Main.rs[i].y1 == activey[0]  && Main.rs[i].x2 == activex[1]  && Main.rs[i].y2 == activey[1]) || (Main.rs[i].x1 == activex[1]  && Main.rs[i].y1 == activey[1]  && Main.rs[i].x2 == activex[0]  && Main.rs[i].y2 == activey[0]) )
						{
							Main.rs[i] = new Resistor();
							flag  = 1;
							
						}
					}
				}
			}
		
			if(flag == 1)
			{
				jp2.removeAll();
				
				
				for(int i = 0;i<15;i++)
			    {
			      for(int j = 0;j<15;j++)
			      {	  
			    	 junction[i][j] = new JButton(); 
			    	 junction_bool[i][j] = false;
			         junction[i][j].setBounds(40+i*43,30+j*43,5,5);
			         junction[i][j].setBackground(Color.GRAY);
			         junction[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			         junction[i][j].addActionListener(this);
			         jp2.add(junction[i][j]);
			         
			      }   
			    }
				
				dC = new drawCoordinates();
			    jp2.add(dC);
			    dC.setBounds(0,0,800,800);
				// Earthing :
				
				
				if(Main.earthingx !=-1 && Main.earthingy!=-1)
				{
				   dG = new drawGround(40+Main.earthingx*43,30+Main.earthingy*43);	
				   junction_bool[Main.earthingx][Main.earthingy] = false;
				   junction[Main.earthingx][Main.earthingy].setSize(7,7);
				   junction[Main.earthingx][Main.earthingy].setBackground(Color.green);
			       jp2.add(dG);
			       dG.setBounds(0,0,800,800);
				}
				
				
				// DC :
				
				if(Main.dc.x1 !=-1 && Main.dc.y1 != -1  && Main.dc.x2 != -1 && Main.dc.y2 != -1)
				{
					double value = Main.dc.voltage;
					if(Main.dc.rtol)
					
				    	 dD  = new drawDc(40+Main.dc.x2*43,30+Main.dc.y2*43,40+Main.dc.x1*43,30+Main.dc.y1*43,value);
				
				    else
				    
				    	 dD  = new drawDc(40+Main.dc.x1*43,30+Main.dc.y1*43,40+Main.dc.x2*43,30+Main.dc.y2*43,value);
				    
				  jp2.add(dD);
				  dD.setBounds(0,0,800,800);
				  junction_bool[Main.dc.x1][Main.dc.y1] = false;
				  junction[Main.dc.x1][Main.dc.y1].setSize(7,7);
				  junction[Main.dc.x1][Main.dc.y1].setBackground(Color.green);
			      
				  
			      junction_bool[Main.dc.x2][Main.dc.y2] = false;
				  junction[Main.dc.x2][Main.dc.y2].setSize(7,7);
				  junction[Main.dc.x2][Main.dc.y2].setBackground(Color.green);
				}
				
				// Resistor :
				
				
			   for(int i = 0;i<Main.rs.length;i++)	
			   {	
				
				if(Main.rs[i].x1 !=-1 && Main.rs[i].y1 != -1  && Main.rs[i].x2 != -1 && Main.rs[i].y2 != -1 && Main.rs[i].resistance !=-1)  
				{	 
				 double value = Main.rs[i].resistance;
				  if(value == 0.0)
				 {
					 if(Main.rs[i].x1 > Main.rs[i].x2)
						 dW  = new drawWire(40+Main.rs[i].x2*43,30+Main.rs[i].y2*43,40+Main.rs[i].x1*43,30+Main.rs[i].y1*43);
					 else if(Main.rs[i].x1 == Main.rs[i].x2 && Main.rs[i].y1>Main.rs[i].y2)
				
						 dW  = new drawWire(40+Main.rs[i].x2*43,30+Main.rs[i].y2*43,40+Main.rs[i].x1*43,30+Main.rs[i].y1*43);
					
					 else 
					    dW  = new drawWire(40+Main.rs[i].x1*43,30+Main.rs[i].y1*43,40+Main.rs[i].x2*43,30+Main.rs[i].y2*43);
					  jp2.add(dW);
					  dW.setBounds(0,0,800,800);
				 }
				 else
				 {
					if(Main.rs[i].x1 > Main.rs[i].x2)
					  dR  = new drawResistor(40+Main.rs[i].x2*43,30+Main.rs[i].y2*43,40+Main.rs[i].x1*43,30+Main.rs[i].y1*43,value);
				   else if(Main.rs[i].x1 == Main.rs[i].x2 && Main.rs[i].y1>Main.rs[i].y2)
			
					 dR  = new drawResistor(40+Main.rs[i].x2*43,30+Main.rs[i].y2*43,40+Main.rs[i].x1*43,30+Main.rs[i].y1*43,value);
				
				   else 
				     dR  = new drawResistor(40+Main.rs[i].x1*43,30+Main.rs[i].y1*43,40+Main.rs[i].x2*43,30+Main.rs[i].y2*43,value);
				     jp2.add(dR);
				     dR.setBounds(0,0,800,800);
				 }
				  
				  junction_bool[Main.rs[i].x1][Main.rs[i].y1] = false;
				  junction[Main.rs[i].x1][Main.rs[i].y1].setSize(7,7);
				  junction[Main.rs[i].x1][Main.rs[i].y1].setBackground(Color.green);
			      
				  
			      junction_bool[Main.rs[i].x2][Main.rs[i].y2] = false;
				  junction[Main.rs[i].x2][Main.rs[i].y2].setSize(7,7);
				  junction[Main.rs[i].x2][Main.rs[i].y2].setBackground(Color.green);
			      
			      
				}
			   }
				
				
				
				
				
				
			    jp2.repaint();
				jp2.validate();
				
				
			    junction_test = 0;
			    activex[0] = activex[1] = -1;
				activey[0] = activey[1] = -1;	
			}
			
			
			
		}
		if(ae.getSource() == dc)
		{
			if(junction_test == 2)
			  {
				obj.setEnabled(false);
				DCsourceHandler  dcr = new DCsourceHandler(obj,activex[0],activey[0],activex[1],activey[1]);
				if(dcr.dialogboxresistor() == 0)
				{
					Main mn = new Main();
					double value = mn.dc.voltage;
				     if(dcr.rtol)
				    	 dD  = new drawDc(40+activex[1]*43,30+activey[1]*43,40+activex[0]*43,30+activey[0]*43,value);
				     else
				    	 dD  = new drawDc(40+activex[0]*43,30+activey[0]*43,40+activex[1]*43,30+activey[1]*43,value);
				  
				  jp2.add(dD);
				  
				  junction_bool[activex[0]][activey[0]] = false;
				  junction[activex[0]][activey[0]].setSize(7,7);
				  junction[activex[0]][activey[0]].setBackground(Color.green);
			      junction_test--;
				  
			      junction_bool[activex[1]][activey[1]] = false;
				  junction[activex[1]][activey[1]].setSize(7,7);
				  junction[activex[1]][activey[1]].setBackground(Color.green);
			      junction_test--;
			      activex[0] = activey[0] = activex[1] = activey[1] = -1;
			      dD.setBounds(0,0,800,800);
				} 
			  } 
		}
		
		if(ae.getSource() == R)
		{
			if(junction_test == 2)
			  {
				obj.setEnabled(false);
				resistorHandler rh = new resistorHandler(obj,activex[0],activey[0],activex[1],activey[1]);
				if(rh.dialogboxresistor() == 0)
				{	
				 Main mn = new Main();
				 Resistor r = mn.rs[mn.rspointer-1];
				 double value = r.resistance;
				 if(activex[0] > activex[1])
					 dR  = new drawResistor(40+activex[1]*43,30+activey[1]*43,40+activex[0]*43,30+activey[0]*43,value);
				 else if(activex[0] == activex[1] && activey[0]>activey[1])
			
					 dR  = new drawResistor(40+activex[1]*43,30+activey[1]*43,40+activex[0]*43,30+activey[0]*43,value);
				
				 else 
				    dR  = new drawResistor(40+activex[0]*43,30+activey[0]*43,40+activex[1]*43,30+activey[1]*43,value);
				  jp2.add(dR);
				 
				  del = dR;
					
				  
				  junction_bool[activex[0]][activey[0]] = false;
				  junction[activex[0]][activey[0]].setSize(7,7);
				  junction[activex[0]][activey[0]].setBackground(Color.green);
			      junction_test--;
				  
			      junction_bool[activex[1]][activey[1]] = false;
				  junction[activex[1]][activey[1]].setSize(7,7);
				  junction[activex[1]][activey[1]].setBackground(Color.green);
			      junction_test--;
			      activex[0] = activey[0] = activex[1] = activey[1] = -1;
			      dR.setBounds(0,0,800,800);
				}
			
			}
		}
		
		if(ae.getSource() == Wire)
		{
			if(junction_test == 2)
			  {
				resistorHandler rh = new resistorHandler(obj,true,activex[0],activey[0],activex[1],activey[1]);
				 if(activex[0] > activex[1])
					 dW  = new drawWire(40+activex[1]*43,30+activey[1]*43,40+activex[0]*43,30+activey[0]*43);
				 else if(activex[0] == activex[1] && activey[0]>activey[1])
			
					 dW  = new drawWire(40+activex[1]*43,30+activey[1]*43,40+activex[0]*43,30+activey[0]*43);
				
				 else 
				    dW  = new drawWire(40+activex[0]*43,30+activey[0]*43,40+activex[1]*43,30+activey[1]*43);
				  jp2.add(dW);
				  
				  junction_bool[activex[0]][activey[0]] = false;
				  junction[activex[0]][activey[0]].setSize(7,7);
				  junction[activex[0]][activey[0]].setBackground(Color.green);
			      junction_test--;
				  
			      junction_bool[activex[1]][activey[1]] = false;
				  junction[activex[1]][activey[1]].setSize(7,7);
				  junction[activex[1]][activey[1]].setBackground(Color.green);
			      junction_test--;
			      activex[0] = activey[0] = activex[1] = activey[1] = -1;
			      dW.setBounds(0,0,800,800);
				 
			}
		}
		
		for(int i= 0;i<15;i++)
			for(int j = 0;j<15;j++)
				if(ae.getSource() == junction[i][j]) 
				{	
					if(junction_bool[i][j] == false && junction_test < 2)
					{
					  junction_bool[i][j] = true;
					  junction[i][j].setSize(8,8);
					  junction[i][j].setBackground(Color.RED);
					  junction_test++;
					  if(junction_test == 1)
					  {
						  activex[0] = i;
						  activey[0] = j;
					  }
					  else
					  {
						  activex[1] = i;
						  activey[1] = j;
					  }
					  
					}
					else if(junction_bool[i][j] == false && junction_test == 2)
					{
						// Extra Press... 
						   ;
					}
					else
					{
					
						  
							  if(Main.earthingx != i && Main.earthingy != j && ((Main.dc.x1 != i && Main.dc.y1 != j) ||  (Main.dc.x2 != i && Main.dc.y2 !=j)))
							  {
								  int flag = 0;
								  for(int i1 = 0;i1<Main.rs.length;i1++)
								  {
									  if((Main.rs[i1].x1 == i && Main.rs[i1].y1 == j) ||  (Main.rs[i1].x2 == i && Main.rs[i1].y2 ==j))
									  {
										  flag = 1;
										  break;
									  }
								  }
								  if(flag == 0)
								  {
									  junction[i][j].setSize(5,5);
									  junction[i][j].setBackground(Color.GRAY);
								  }
							  
								  else
								  {
									  junction[i][j].setSize(7,7);
									  junction[i][j].setBackground(Color.green);
								  }
						  
						     }
							  else
							  {
								  junction[i][j].setSize(7,7);
								  junction[i][j].setBackground(Color.green);
							  }
							  
							  junction_bool[i][j] = false;
							  
						      junction_test--;
						      if(junction_test == 1)
						      {
						    	  activex[1] = -1;
						    	  activey[1] = -1;
						      }
						      else
						      {
						    	  activex[0] = -1;
						    	  activey[0] = -1;
						      }
							  
					}
				}	
	} 
}

package Controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import View.*;
import Model.*;

public class resistorHandler implements ActionListener  {

   	
   JFrame j;
   Resistor r;
   JPanel jp;
   JRadioButton jrb,jrb1;
   JTextField jtf;
   JLabel band1,band2,mult,tol;
   JComboBox band1combo,band2combo,multcombo,tolcombo;
   int x1,x2,y1,y2;
   public resistorHandler(JFrame jF,boolean wire,int x1,int x2,int y1,int y2)
   {
	   r = new Resistor(0.0,x1,x2,y1,y2);
	   Main mn = new Main();
	   mn.rs[mn.rspointer] = r;
	   mn.rspointer++;
   }
   
   public resistorHandler(JFrame jF,int x1,int x2,int y1,int y2) {
	// TODO Auto-generated constructor stub
	    j = jF; 
	    this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	    jp = new JPanel(new GridLayout(15,15,2,2));
	    jrb = new JRadioButton("Resistor Value (ohm) : ");
	    jrb.setSelected(true);
	    jrb.addActionListener(this);
	    jp.add(jrb);
	    
	    jtf = new JTextField();
	    jtf.setText(" 5");
	    jp.add(jtf);
	    
	    jp.add(Box.createRigidArea(new Dimension(5,0)));
	    jp.add(new JSeparator());
	    
	    jrb1 = new JRadioButton("Resistor Value (Color Coding) : ");
	    jrb1.setSelected(false);
	    jrb1.addActionListener(this);
	    jp.add(jrb1);
	    
	    
	    band1 = new JLabel("Band 1 : ");
	    band2 = new JLabel("Band 2 : ");
	    mult = new JLabel("Multiplier : ");
	    tol = new JLabel("Tolerence : ");
	    band1.setEnabled(false);
	    band2.setEnabled(false);
	    mult.setEnabled(false);
	    tol.setEnabled(false);
	    jp.add(band1);
	    
	    String[] colorcode1 = {
	    		"black",
	            "brown",
	            "red",
	            "orange",
	            "yellow",
	            "green",
	            "blue",
	            "violet",
	            "gray","white"
	   };
	    
	    String[] colorcode2 = {
	    		"green",
	    		"black",
	            "brown",
	            "red",
	            "orange",
	            "yellow",
	            "blue",
	            "violet",
	            "gray","white"
	   };
	  
	    
	    String[] colorcode3 = {
	    		"black",
	            "brown",
	            "red",
	            "orange",
	            "yellow",
	            "green",
	            "blue",
	            "violet",
	            "gray","white","silver","gold"
	   };
	    
	    String[] colorcode4 = {
	            "none",
	    		"brown",
	            "red",
	            "yellow",
	            "green",
	            "blue",
	            "violet",
	            "gray","silver","gold"
	   }; 
	    
	   band1combo = new JComboBox(colorcode1);
	   band1combo.setEnabled(false);
	   jp.add(band1combo);
	   
	   jp.add(band2);
	   
	   band2combo = new JComboBox(colorcode2);
	   band2combo.setEnabled(false);
	   jp.add(band2combo);
	   
	   jp.add(mult);
	   
	   multcombo = new JComboBox(colorcode3);
	   multcombo.setEnabled(false);
	   jp.add(multcombo);
	    
	   jp.add(tol);
	   
	   tolcombo = new JComboBox(colorcode4);
	   tolcombo.setEnabled(false);
	   jp.add(tolcombo);
	    
   }
   public int dialogboxresistor()
   {
	   int val = JOptionPane.showConfirmDialog(j,jp,"Resistor Settings : ",
               JOptionPane.OK_CANCEL_OPTION,
               JOptionPane.PLAIN_MESSAGE);
	   
	   if(val == 0)
	   {
		   if(jrb.isSelected())
		   {
			   String Value = jtf.getText();
			   r = new Resistor(Double.parseDouble(Value.trim()),x1,x2,y1,y2);
		   }
		   else
		   {
			   String color[] = new String[4];
			   color[0] = (String)band1combo.getSelectedItem();
			   color[1] = (String)band2combo.getSelectedItem();
			   color[2] = (String)multcombo.getSelectedItem();
			   color[3] = (String)tolcombo.getSelectedItem();
			   r = new Resistor(color[0], color[1], color[2], color[3],x1,x2,y1,y2);
		   }
	   }
	   
	   
	   Main.rs[Main.rspointer] = r;
	   Main.rspointer++;
	   	   
	   j.setEnabled(true);
	   
	   return val;
   }
@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
	if(arg0.getSource() == jrb)
	{
		jrb1.setSelected(false);
		tolcombo.setEnabled(false);
		multcombo.setEnabled(false);
		band2combo.setEnabled(false);
		band1combo.setEnabled(false);
		band1.setEnabled(false);
	    band2.setEnabled(false);
	    mult.setEnabled(false);
	    tol.setEnabled(false);
	    
	    jtf.setEnabled(true);
	    
	}
	if(arg0.getSource() == jrb1)
	{
		jrb.setSelected(false);
		jtf.setEnabled(false);
		
		tolcombo.setEnabled(true);
		multcombo.setEnabled(true);
		band2combo.setEnabled(true);
		band1combo.setEnabled(true);
		band1.setEnabled(true);
	    band2.setEnabled(true);
	    mult.setEnabled(true);
	    tol.setEnabled(true);
		
	}
	
}

}

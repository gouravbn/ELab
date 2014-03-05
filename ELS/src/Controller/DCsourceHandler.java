package Controller;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import Model.*;

public class DCsourceHandler implements ActionListener  {

   	
   JFrame j;
   DCsource r;
   JPanel jp;
   JLabel jl,Direction;
   JTextField jtf;
   JRadioButton jrb,jrb1;
   public boolean rtol;
   int x1,x2,y1,y2;
   public DCsourceHandler(JFrame jF,int x1,int x2,int y1,int y2) {
	// TODO Auto-generated constructor stub
	    j = jF; 
	    
	    jp = new JPanel(new GridLayout(10,10,2,2));
	    jl  = new JLabel("Source Voltage (volt) : ");
	    jp.add(jl);
	    
	    this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	    
	    jtf = new JTextField();
	    jtf.setText(" 10");
	    jp.add(jtf);
	    
	    jp.add(Box.createRigidArea(new Dimension(5,0)));
	    jp.add(new JSeparator());
	    
	    jrb = new JRadioButton("Junction 1 to Junction 2");
	    jrb.setSelected(true);
	    jrb.addActionListener(this);
	    jp.add(jrb);
	    
	    jrb1 = new JRadioButton("Junction 2 to Junction 1");
	    jrb1.setSelected(false);
	    jrb1.addActionListener(this);
	    jp.add(jrb1);
	    
	    
   }
   public int dialogboxresistor()
   {
	   int val = JOptionPane.showConfirmDialog(j,jp,"JOptionPane Example : ",
               JOptionPane.OK_CANCEL_OPTION,
               JOptionPane.PLAIN_MESSAGE);
	   
	   if(val == 0)
	   {
		   String Value = jtf.getText();
		   if(jrb.isSelected())
			   rtol = false;
		   else
			   rtol = true;
		   r = new DCsource(Double.parseDouble(Value.trim()), rtol,x1,x2,y1,y2);
		   Main.dc = r;	   
	   }
	   
	   
	  
	   	  
	   j.setEnabled(true);
	   
	   return val;
   }
@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
	if(arg0.getSource() == jrb)
		jrb1.setSelected(false);
	
	if(arg0.getSource() == jrb1)
	   jrb.setSelected(false);
	
	
}

}


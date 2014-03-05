package View;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Controller.*;
public class drawGround extends JComponent {

    int x,y;
   
	public drawGround(int a,int b) {
		x = a+3;y = b+3;
        setBackground(Color.BLACK);
        setPreferredSize( new Dimension(164, 164) );
        
       
     }

     public void paintComponent(Graphics g) {
        	
    	Graphics2D g2d = (Graphics2D)g; 
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(x,y,x,y+10);
        g2d.drawLine(x-15, y + 10, x +15, y + 10);
        g2d.drawLine(x-10, y + 13, x +10, y + 13);
        g2d.drawLine(x-5, y + 16, x +5, y + 16);
        g2d.drawLine(x-1, y + 19, x +1, y + 19);
        
     }
     
  
}


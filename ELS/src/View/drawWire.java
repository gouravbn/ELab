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

public class drawWire extends JComponent {

    int x1,y1,x2,y2;
    double distance;
	public drawWire(int a,int b,int c,int d) {
		x1 = a+2;y1 =b+2; x2 = c+2;y2 = d+2;
        setBackground(Color.BLACK);
        setPreferredSize( new Dimension(164, 164) );
        dist();
     }
	public void dist()
	{
		distance =  Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		
	}
     
     public void paintComponent(Graphics g) {
        	
    	Graphics2D g2d = (Graphics2D)g; 
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        
        g2d.drawLine(x1,y1,x2,y2);
        
        
     }
     
  
}


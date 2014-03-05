package View;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

public class drawCoordinates extends JComponent {

    int x1,y1,x2,y2;
    double distance;
	public drawCoordinates() {
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
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setFont(new Font("default", Font.BOLD, 14));
        for ( int i = 0;i<15;i++)
           g2d.drawString(Integer.toString(i),40+i*43-1,15);
        for ( int i = 0;i<15;i++)
            g2d.drawString(Integer.toString(i),15,30+i*43+8);
        
        
     }
     
  
}


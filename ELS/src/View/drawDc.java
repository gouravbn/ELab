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

public class drawDc extends JComponent {

    int x1,y1,x2,y2;
    double distance;
    double Val;
	public drawDc(int a,int b,int c,int d,double value) {
		Val = value;
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
        
        g2d.drawLine(x1,y1,x1+(int)(0.45*(x2-x1)),y1+(int)(0.45*(y2-y1)));
        g2d.drawLine(x1+(int)(0.53*(x2-x1)),y1+(int)(0.53*(y2-y1)),x2,y2);
      
        
        double taninverse = Math.atan(((double)(y2-y1))/((double)(x2-x1)));
        if(x2 - x1 == 0)
        	taninverse = Math.PI/2;
        Rectangle rect2,redrect;
        int thickness = 2;
        if(x2 - x1 >0){
        	g2d.drawString(Val + " volt ",x1+(int)(0.45*(x2-x1)), y1+(int)(0.45*(y2-y1)) - (int)((x2-x1)/20)-10);
            redrect = new Rectangle(x1+(int)(0.45*(x2-x1)), y1+(int)(0.45*(y2-y1)) - (int)((x2-x1)/20), 1 , (int)((x2-x1)/10));
            rect2 = new Rectangle(x1+(int)(0.54*(x2-x1)), y1+(int)(0.54*(y2-y1)) -(int)((x2-x1)/10), 1 , (int)((x2-x1)/5));
        }
        else
        {
        	g2d.drawString(Val + " volt ",x1+(int)(0.45*(x2-x1)), y1+(int)(0.45*(y2-y1)) - (int)((x1-x2)/20)-10);
        	redrect = new Rectangle(x1+(int)(0.45*(x2-x1)), y1+(int)(0.45*(y2-y1)) - (int)((x1-x2)/20), 1 , (int)((x1-x2)/10));
            rect2 = new Rectangle(x1+(int)(0.54*(x2-x1)), y1+(int)(0.54*(y2-y1)) -(int)((x1-x2)/10), 1 , (int)((x1-x2)/5));
        	
        }
        
        //g2d.drawString(Val + " volt ",x1+(int)(0.45*(x2-x1)), y1+(int)(0.45*(y2-y1)) - (int)((x1-x2)/20)-10);
        g2d.rotate(taninverse, x1 + (int)(0.50*(x2-x1)),y1+ (int)(0.50*(y2-y1)));
        g2d.draw(rect2);
        g2d.fill(rect2);
        
        //g2d.rotate(taninverse,x1 + (int)(0.53*(x2-x1)),y1+ (int)(0.53*(y2-y1)));
        g2d.draw(redrect);
        g2d.fill(redrect);
        
        
     }
     
  
}


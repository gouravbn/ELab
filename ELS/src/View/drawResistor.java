package View;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class drawResistor extends JComponent {

    int x1,y1,x2,y2;
    double distance;
    double Val;
	public drawResistor(int a,int b,int c,int d,double value) {
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
        
         
        
        g2d.drawLine(x1,y1,x1+(int)(0.25*(x2-x1)),y1+(int)(0.25*(y2-y1)));
        g2d.drawLine(x1+(int)(0.75*(x2-x1)),y1+(int)(0.75*(y2-y1)),x2,y2);
        
       
        //double l = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)) / 8;
        
        double taninverse = Math.atan(((double)(y2-y1))/((double)(x2-x1)));
        if(x2 - x1 == 0)
        	taninverse = Math.PI/2;
        int x = x1+(int)(0.25*(x2-x1)),y = y1+(int)(0.25*(y2-y1));
        Rectangle rect2,redrect,redrect1,redrect2,redrect3;
        if(x2-x1 !=0)
        {
        rect2 = new Rectangle(x, y-(int)((x2-x1)/10), (int)(0.5 * Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))) , (int)((x2-x1)/5));
        redrect = new Rectangle(x + (int)((x2-x1)/10), y-(int)((x2-x1)/10), (int)(0.5 * Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))/40) , (int)((x2-x1)/5));
        redrect1 = new Rectangle(x + (int)(1.7*(x2-x1)/10), y-(int)((x2-x1)/10), (int)(0.5 * Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))/40) , (int)((x2-x1)/5));
        redrect2 = new Rectangle(x + (int)(2.4*(x2-x1)/10), y-(int)((x2-x1)/10), (int)(0.5 * Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))/40) , (int)((x2-x1)/5));
        redrect3 = new Rectangle(x + (int)(4.4*(x2-x1)/10), y-(int)((x2-x1)/10), (int)(0.5 * Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))/40) , (int)((x2-x1)/5));
        }
        else
        {
        	rect2 = new Rectangle(x, y-(int)((y2-y1)/10), (int)(0.5 * Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))) , (int)((y2-y1)/5));
            redrect = new Rectangle(x + (int)((y2-y1)/10), y-(int)((y2-y1)/10), (int)(0.5 * Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))/40) , (int)((y2-y1)/5));
            redrect1 = new Rectangle(x + (int)(1.7*(y2-y1)/10), y-(int)((y2-y1)/10), (int)(0.5 * Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))/40) , (int)((y2-y1)/5));
            redrect2 = new Rectangle(x + (int)(2.4*(y2-y1)/10), y-(int)((y2-y1)/10), (int)(0.5 * Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))/40) , (int)((y2-y1)/5));
            redrect3 = new Rectangle(x + (int)(4.4*(y2-y1)/10), y-(int)((y2-y1)/10), (int)(0.5 * Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))/40) , (int)((y2-y1)/5));
            
        }
       
        AffineTransform orig = g2d.getTransform();
        g2d.rotate(taninverse,x,y);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawString(Val + " ohm ",x, y-(int)((x2-x1)/10)-10);
        g2d.setTransform(orig);
        g2d.setColor(new Color(210, 105, 30));
        
        g2d.rotate(taninverse, x, y);
        g2d.draw(rect2);
        g2d.fill(rect2);
        
        g2d.setColor(Color.RED);
        g2d.draw(redrect);
        g2d.fill(redrect);
        
        g2d.setColor(Color.BLACK);
        g2d.draw(redrect1);
        g2d.fill(redrect1);
        
        g2d.setColor(Color.GREEN);
        g2d.draw(redrect2);
        g2d.fill(redrect2);
        
        g2d.setColor(Color.BLUE);
        g2d.draw(redrect3);
        g2d.fill(redrect3);
        
        
        
     }
     
  
}


package Model;

public class DCsource {
	public double voltage;	
	public boolean rtol;
	public int x1,x2,y1,y2;
	public DCsource(){
		x1 = x2 = y1 = y2 = -1;
		voltage = 0;
		rtol = false;
	}
	public DCsource(double v, boolean val,int x1,int y1,int x2,int y2)
	{
		voltage = v;
		rtol = val;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	public void print(){
		System.out.println("Voltage = "+voltage);
		System.out.println(" right to left : "+rtol);
	}
}
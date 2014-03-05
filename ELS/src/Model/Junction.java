package Model;

public class Junction {
	public boolean valid;
	public int x,y;
	public double voltage;
	public Junction(){
		x = y = 0;
		voltage = 0;
		valid = false;
	}
	public void print(){
		System.out.println("Voltage = "+voltage+"\tJunction:"+x+","+y+"\tValidity:"+valid);
	}
}

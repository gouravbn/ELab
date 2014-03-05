package Model;

public class Resistor {
	public double resistance;
	public int x1,x2,y1,y2;
	public Resistor(){
		resistance = -1;
		x1 = -1;x2 = -1;y1 = -1;y2 = -1;
	}
	public Resistor(double value,int x1,int y1,int x2,int y2){
		resistance = value;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	public Resistor(String s1,String s2, String s3, String s4,int x1,int y1,int x2,int y2){
		color c = new color(s1,s2,s3,s4);
		resistance = ((c.Iband)*10 + c.IIband)*c.multiplier;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	public void print(){
		System.out.println("Resistance = "+resistance);
	}
}

class color{
	double Iband;
	double IIband;
	double multiplier;
	double tolerance;
	color(String s1, String s2, String s3, String s4){
		if(s1=="black") Iband=0;
		else if(s1=="brown") Iband=1;
		else if(s1=="red") Iband=2;
		else if(s1=="orange") Iband=3;
		else if(s1=="yellow") Iband=4;
		else if(s1=="green") Iband=5;
		else if(s1=="blue") Iband=6;
		else if(s1=="violet") Iband=7;
		else if(s1=="gray") Iband=8;
		else if(s1=="white") Iband=9;
		
		if(s2=="black") IIband=0;
		else if(s2=="brown") IIband=1;
		else if(s2=="red") IIband=2;
		else if(s2=="orange") IIband=3;
		else if(s2=="yellow") IIband=4;
		else if(s2=="green") IIband=5;
		else if(s2=="blue") IIband=6;
		else if(s2=="violet") IIband=7;
		else if(s2=="gray") IIband=8;
		else if(s2=="white") IIband=9;
		
		if(s3=="black") multiplier=Math.pow(10, 0);
		else if(s3=="brown") multiplier=Math.pow(10, 1);
		else if(s3=="red") multiplier=Math.pow(10, 2);
		else if(s3=="orange") multiplier=Math.pow(10, 3);
		else if(s3=="yellow") multiplier=Math.pow(10, 4);
		else if(s3=="green") multiplier=Math.pow(10, 5);
		else if(s3=="blue") multiplier=Math.pow(10, 6);
		else if(s3=="violet") multiplier=Math.pow(10, 7);
		else if(s3=="gray") multiplier=Math.pow(10, 8);
		else if(s3=="white") multiplier=Math.pow(10, 9);
		else if(s3=="gold") multiplier=Math.pow(10, -1);
		else if(s3=="silver") multiplier=Math.pow(10, -2);
		
		if(s4=="brown") tolerance=1;
		else if(s4=="red") tolerance=2;
		else if(s4=="yellow") tolerance=5;
		else if(s4=="green") tolerance=0.5;
		else if(s4=="blue") tolerance=0.25;
		else if(s4=="violet") tolerance=0.1;
		else if(s4=="gray") tolerance=0.05;
		else if(s4=="gold") tolerance=5;
		else if(s4=="silver") tolerance=10;
		else tolerance=20;
	}
}
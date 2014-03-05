package Model;

public class ACsource {
	public double amplitude;
	public double frequency;
	public double phase;
	public boolean function;	   // True represents sine function and False represents cosine  function 
	public double voltage;
	public ACsource(){
		amplitude = 5;
		frequency = 0;
		phase = 0;
		function = false;
		voltage = 5; 
	}
	public ACsource(double amp,double freq,double ph,boolean func){
		amplitude = amp;
		frequency = freq;
		phase = ph;
		function = func;
		double theta = frequency*0 + phase;
		theta = Math.PI*theta/180;
		if(function) voltage = amplitude*Math.sin(theta);
		else voltage = amplitude*Math.cos(theta);
	}
	void current(double time){
		double theta = frequency*time + phase;
		theta = Math.PI*theta/180;
		if(function) voltage = amplitude*Math.sin(theta);
		else voltage = amplitude*Math.cos(theta);
	}
	public void print(){
		System.out.println("Amplitude = "+amplitude);
		System.out.println("Frequency = "+frequency);
		System.out.println("Phase = "+phase);
		if(function) System.out.println("Function = Sine");
		else System.out.println("Function = Cosine");
		System.out.println("Voltage = "+voltage);
	}
}
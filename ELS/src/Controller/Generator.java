package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Generator {

	public Generator(File f,String project_name) throws IOException
	{
		 FileWriter fw = new FileWriter(f.getAbsoluteFile());
	     BufferedWriter bw = new BufferedWriter(fw);
		 bw.write("$$ ELS v1.0 @iitropar $$\n");
		 
		 bw.write("\n <Project Name : "+project_name+"\\>\n");
		 bw.write("\n \t <Circuit>");
		 bw.write("\n \t \t <cid : 1>");
		 for(int i = 0;i<Main.rs.length;i++)
		 {
			 if(Main.rs[i].resistance != -1)
			 {
				 if(Main.rs[i].resistance == 0.0)
				 {
					 bw.write("\n \t \t <Wire>");
					 bw.write("\n \t \t \t <wx1> "+Main.rs[i].x1+" </wx1>");
					 bw.write("\n \t \t \t <wy1> "+Main.rs[i].y1+" </wy1>");
					 bw.write("\n \t \t \t <wx2> "+Main.rs[i].x2+" </wx2>");
					 bw.write("\n \t \t \t <wy2> "+Main.rs[i].y2+" </wy2>");
					 bw.write("\n \t \t </Wire>\n"); 
				 }
				 else
				 {
					 bw.write("\n \t \t <Resistor>");
					 bw.write("\n \t \t \t <rvalue> "+Main.rs[i].resistance+" </rvalue>");
					 bw.write("\n \t \t \t <rx1> "+Main.rs[i].x1+" </rx1>");
					 bw.write("\n \t \t \t <ry1> "+Main.rs[i].y1+" </ry1>");
					 bw.write("\n \t \t \t <rx2> "+Main.rs[i].x2+" </rx2>");
					 bw.write("\n \t \t \t <ry2> "+Main.rs[i].y2+" </ry2>");
					 bw.write("\n \t \t </Resistor>\n"); 
				 }
				 
			 }
		 }
		 if(Main.dc.voltage !=-1)
		 {
			 bw.write("\n \t \t <DC Source>");
			 bw.write("\n \t \t \t <dcvalue> "+Main.dc.voltage+" </dcvalue>");
			 bw.write("\n \t \t \t <dcx1> "+Main.dc.x1+" </dcx1>");
			 bw.write("\n \t \t \t <dcy1> "+Main.dc.y1+" </dcy1>");
			 bw.write("\n \t \t \t <dcx2> "+Main.dc.x2+" </dcx2>");
			 bw.write("\n \t \t \t <dcy2> "+Main.dc.y2+" </dcy2>");
			 bw.write("\n \t \t </DC Source>\n");
		 }
		 if(Main.earthingx !=-1 && Main.earthingy !=-1)
		 {
			 bw.write("\n \t \t <Earthing>");
			 bw.write("\n \t \t \t <ecx> "+Main.earthingx+" </ecy>");
			 bw.write("\n \t \t \t <ecy> "+Main.earthingy+" </ecy>");
			 bw.write("\n \t \t </Earthing>\n");
		 }
		 bw.write("\n \t </Circuit>");
	     bw.close();
	}
	
}

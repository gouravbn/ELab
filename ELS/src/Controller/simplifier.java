package Controller;

import Model.DCsource;
import Model.Resistor;

public class simplifier {
	
	public simplifier()
	{
		simplify();
		
	}

	static Resistor s_rs[] = new Resistor[15*7];
	static DCsource s_dc = new DCsource();
	static double s_earthingx=Main.earthingx;
	static double s_earthingy=Main.earthingy;
	public void simplify(){
		for(int i = 0; i<s_rs.length;i++)
			s_rs[i] = new Resistor();
		s_dc.x1=Main.dc.x1;
		s_dc.x2=Main.dc.x2;
		s_dc.y1=Main.dc.y1;
		s_dc.y2=Main.dc.y2;
		s_dc.voltage=Main.dc.voltage;
		s_dc.rtol=Main.dc.rtol;
		
		Resistor srs[] = new Resistor[15*7];
		for(int i=0;i<Main.rs.length;i++){
			if(Main.rs[i].resistance == -1)
			break;
			srs[i]=new Resistor();
			srs[i].x1=Main.rs[i].x1;
			srs[i].x2=Main.rs[i].x2;
			srs[i].y1=Main.rs[i].y1;
			srs[i].y2=Main.rs[i].y2;
			srs[i].resistance=Main.rs[i].resistance;
					
		}
		for(int i=0;i<srs.length;i++){
			if(srs[i]==null) break;
			if(srs[i].resistance==0){
				
				System.out.print(srs[i].x1 + " " + srs[i].y1 );
				if(s_dc.x1==srs[i].x1  &&  s_dc.y1==srs[i].y1){
					s_dc.x1=srs[i].x2;
					s_dc.y1=srs[i].y2;
					System.out.print("eye1");
					
				}
				if(s_dc.x2==srs[i].x1  &&  s_dc.y2==srs[i].y1){
					s_dc.x2=srs[i].x2;
					s_dc.y2=srs[i].y2;
					
				}

				if(srs[i].x1==s_earthingx && srs[i].y1==s_earthingy){
					s_earthingx=srs[i].x2;
					s_earthingy=srs[i].y2;
					
				}
				for(int j=0;j<srs.length;j++){
					if(srs[j]==null) break;
					if(srs[i].x1==srs[j].x1 && srs[i].y1==srs[j].y1 && j!=i){
						srs[j].x1=srs[i].x2;
						srs[j].y1=srs[i].y2;
						
					}
					if(srs[i].x1==srs[j].x2 && srs[i].y1==srs[j].y2 && j!=i){
						srs[j].x2=srs[i].x2;
						srs[j].y2=srs[i].y2;
						System.out.print("eye2");
						
					}
				}
				
			}
		}
		int y=0;
		System.out.println("Simplified Circuit");
		for(int i=0;i<srs.length;i++){
			if(srs[i]==null) break;

			if(srs[i].resistance!=0){
				s_rs[y]=new Resistor();
				s_rs[y].x1=srs[i].x1;
				System.out.print(s_rs[y].x1+"\t");
				s_rs[y].x2=srs[i].x2;
				s_rs[y].y1=srs[i].y1;
				System.out.print(s_rs[y].y1+"\t");
				System.out.print(s_rs[y].x2+"\t");
				s_rs[y].y2=srs[i].y2;
				System.out.print(s_rs[y].y2+"\t");
				s_rs[y].resistance=srs[i].resistance;
				System.out.print(s_rs[y].resistance+"\n");
				y++;
			}
		}
		System.out.println("----------------");
		System.out.println("Simplified battery");
		s_dc.print();
		System.out.println("----------------");
		System.out.println("Simplified earthing");
		System.out.println(s_earthingx+" "+s_earthingy);

	}
}

package Controller;

import Model.Junction;

public class solver {
	public Junction answer[];
	public solver()
	{
		answer = solve();
	}
	public double determinant(double a[][], int n){
		
		double det = 0;int sign = 1, p = 0, q = 0;

		if(n==1){
			det = a[0][0];
		}
		else{
			double b[][] = new double[n-1][n-1];
			for(int x = 0 ; x < n ; x++){
				p=0;q=0;
				for(int i = 1;i < n; i++){
					for(int j = 0; j < n;j++){
						if(j != x){
							b[p][q++] = a[i][j];
							if(q % (n-1) == 0){
								p++;
								q=0;
							}
						}
					}
				}
				det = det + a[0][x] *
	                              determinant(b, n-1) *
	                              sign;
				sign = -sign;
			}
		}
		return det;
	}
	Junction[] solve(){	
		boolean valid_junction[][] = new boolean[15][15];
		int number_of_junctions = 0;
		for(int i=0;i<simplifier.s_rs.length;i++){
			if(simplifier.s_rs[i].resistance==-1) break;
			if(!valid_junction[simplifier.s_rs[i].x1][simplifier.s_rs[i].y1]){
				number_of_junctions++;
				valid_junction[simplifier.s_rs[i].x1][simplifier.s_rs[i].y1] = true;
			}
			if(!valid_junction[simplifier.s_rs[i].x2][simplifier.s_rs[i].y2]){
				number_of_junctions++;
				valid_junction[simplifier.s_rs[i].x2][simplifier.s_rs[i].y2] = true;
			}
			simplifier.s_rs[i].print();
		}
		System.out.println(number_of_junctions);
		Junction junction_voltage[] = new Junction[number_of_junctions];
		int count=0;
		int temp_earthingx,temp_earthingy,temp_knownx,temp_knowny;
		if(!simplifier.s_dc.rtol){
			temp_earthingx = simplifier.s_dc.x1;
			temp_earthingy = simplifier.s_dc.y1;
			temp_knownx = simplifier.s_dc.x2;
			temp_knowny = simplifier.s_dc.y2;
		}
		else{
			temp_earthingx = simplifier.s_dc.x2;
			temp_earthingy = simplifier.s_dc.y2;				
			temp_knownx = simplifier.s_dc.x1;
			temp_knowny = simplifier.s_dc.y1;
		}
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				if(valid_junction[i][j]){
					junction_voltage[count] = new Junction();
					junction_voltage[count].x = i;
					junction_voltage[count].y = j;
					junction_voltage[count].valid = false;
					if(temp_earthingx==i && temp_earthingy==j){
						junction_voltage[count].valid = true;						
					}
					if(temp_knownx==i && temp_knowny==j){
						junction_voltage[count].valid = true;
						junction_voltage[count].voltage = simplifier.s_dc.voltage;
					}
					count++;
				}
			}
		}
		System.out.println(number_of_junctions);
		for(int i=0;i<number_of_junctions;i++){
			junction_voltage[i].print();
		}

		simplifier.s_dc.print();
		
		System.out.println();
		for(int i=0;i<number_of_junctions;i++){
			junction_voltage[i].print();
		}
		simplifier.s_dc.print();
		
		int number_of_variables=0;
		for(int i=0;i<number_of_junctions;i++){
			if(!junction_voltage[i].valid) number_of_variables++;
		}
		System.out.println(number_of_variables);
		
		double matrix_coefficients[][] = new double[number_of_variables][number_of_variables];
		double constant_vector[] = new double[number_of_variables];
		
		System.out.println("Hello");
	
		int m=0,n=0;
		int ctm=0,ctn=0; //to keep track of the known junction voltages
		
		
		while(m<number_of_variables){
			if(junction_voltage[m+ctm].valid) ctm++;
			int mx = junction_voltage[m+ctm].x;
			int my = junction_voltage[m+ctm].y;
			ctn=0;
			n=0;
			while(n<=number_of_variables){
				if((n+ctn)>=number_of_junctions) break;
//				System.out.println("\nNNNNN:"+n+"\t"+ctn);
				if(junction_voltage[n+ctn].valid){
					// Generating constants' vector
					int xx = junction_voltage[n+ctn].x;
					int yy = junction_voltage[n+ctn].y;
//					System.out.println("XXXXXX:"+xx+"\t"+yy);
					for(int i=0;i<simplifier.s_rs.length;i++){
						if(simplifier.s_rs[i].resistance==-1) break;
//						System.out.println("//////"+'\n'+ simplifier.s_rs[i].x1+'\t'+ simplifier.s_rs[i].y1+'\t'+simplifier.s_rs[i].x2+'\t'+simplifier.s_rs[i].y2);
//						System.out.println("kaka\t"+mx+'\t'+my+'\t'+xx+'\t'+yy);

						if((simplifier.s_rs[i].x1==mx && simplifier.s_rs[i].y1==my && simplifier.s_rs[i].x2==xx && simplifier.s_rs[i].y2==yy)){
							constant_vector[m] += (junction_voltage[n+ctn].voltage/simplifier.s_rs[i].resistance);
//							System.out.println("CC:"+constant_vector[m]);
						}
						if((simplifier.s_rs[i].x1==xx && simplifier.s_rs[i].y1==yy && simplifier.s_rs[i].x2==mx && simplifier.s_rs[i].y2==my)){
							constant_vector[m] += (junction_voltage[n+ctn].voltage/simplifier.s_rs[i].resistance);
//							System.out.println("CC1:"+constant_vector[m]);
						}
					}
					ctn++;
				}
				if(n==number_of_variables) {break;}
				int nx = junction_voltage[n+ctn].x;
				int ny = junction_voltage[n+ctn].y;
				if(nx==mx && ny==my){
					double temp=0;
					for(int i=0;i<simplifier.s_rs.length;i++){
						if(simplifier.s_rs[i].resistance==-1) break;
						if((simplifier.s_rs[i].x1==mx && simplifier.s_rs[i].y1==my) || (simplifier.s_rs[i].x2==mx && simplifier.s_rs[i].y2==my)){
							temp += (1/simplifier.s_rs[i].resistance);
						}
						matrix_coefficients[m][n] = temp;
					}
				}
				else{
					for(int i=0;i<simplifier.s_rs.length;i++){
						if(simplifier.s_rs[i].resistance==-1) break;
						if((simplifier.s_rs[i].x1==mx && simplifier.s_rs[i].y1==my && simplifier.s_rs[i].x2==nx && simplifier.s_rs[i].y2==ny)){
							matrix_coefficients[m][n] += (-1/simplifier.s_rs[i].resistance);
						}
						else if((simplifier.s_rs[i].x1==nx && simplifier.s_rs[i].y1==ny && simplifier.s_rs[i].x2==mx && simplifier.s_rs[i].y2==my)){
							matrix_coefficients[m][n] += (-1/simplifier.s_rs[i].resistance);
						}
					}
				}
				System.out.print(matrix_coefficients[m][n]+"\t");
				n++;
			}
			System.out.println();
			m++;
		}
		
		System.out.println("Constant vector");
		for(int i=0;i<number_of_variables;i++){
			System.out.println(constant_vector[i]);
		}
		
		/////////////////////////////////////////////////////////////
		double Inverse[][]=new double[number_of_variables][number_of_variables];
		double answer[]=new double[number_of_variables];	
		if(number_of_variables==0){
		}
		else if(number_of_variables==1){ 
			answer[0] = constant_vector[0]/matrix_coefficients[0][0];
//			System.out.println("�ns\t"+answer[0]);
		}
		else{
			double d0[][]=new double[number_of_variables-1][number_of_variables-1];	// used as co factor matrix for each element
			double inverse[][]=new double[number_of_variables][number_of_variables];		
			for (int i=0;i<number_of_variables;i++){	// row of inverse matrix 
				for (int j=0;j<number_of_variables;j++){	//column of inverse matrix
					int row=0,column=0;	// used as row and column of  co factor matrix 
					for(int k=0;k<number_of_variables;k++){	// inding 3X3 matrix (co factor matrix) for each element at (i,j)
						for (int l=0;l<number_of_variables;l++){
							if(k!=i && l!=j){
								d0[row][column]=matrix_coefficients[k][l];	// creating the matrix
								column++;
								if(column>=number_of_variables-1){	// if all columns are filled moving to next row 
									row++;column=0;
								}
							}
						}
					}
					double element=determinant(d0,number_of_variables-1);	// calling Determinant function to return determinant
					inverse[i][j]=element*Math.pow(-1,(i+j));	//adding element in inverse matrix
				}
			}
			double d=0;
			for(int y=0;y<number_of_variables;y++){
				d+=inverse[y][0]*matrix_coefficients[y][0];
			}
			d=1/d;
			for(int i=0;i<number_of_variables;i++){
				for(int j=0;j<number_of_variables;j++){
					Inverse[j][i]=d*inverse[i][j];
				}
			}
			for(int i=0;i<number_of_variables;i++){
				for(int j=0;j<number_of_variables;j++){
					answer[i]+=Inverse[i][j]*constant_vector[j];
				}		
			}
		}
		///////////////////////////////////////////////////////////////
		for(int j=0;j<number_of_variables;j++){
			System.out.println(answer[j]);	
		}
		
		int ci=0,cti=0;
		double earth_voltage=0;
		while(ci<number_of_junctions){		
			if(junction_voltage[ci].valid){
				if(junction_voltage[ci].x==simplifier.s_earthingx && junction_voltage[ci].y==simplifier.s_earthingy){
					earth_voltage = junction_voltage[ci].voltage;
				}
				ci++;
			}
			else{
				junction_voltage[ci].voltage = answer[cti];
				if(junction_voltage[ci].x==simplifier.s_earthingx && junction_voltage[ci].y==simplifier.s_earthingy){
					earth_voltage = junction_voltage[ci].voltage;
				}
				ci++;cti++;
			}
		}
		ci=0;
		while(ci<number_of_junctions){
			junction_voltage[ci].voltage -= earth_voltage;
			System.out.println("Junction:\t"+junction_voltage[ci].x+"\t"+junction_voltage[ci].y+"\tVoltage\t"+junction_voltage[ci].voltage);
			ci++;
		}
		return junction_voltage;
	}
}

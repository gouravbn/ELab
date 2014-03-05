package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class LoadingRoutine {

	public LoadingRoutine(JPanel jp,JButton jb[][]) throws Exception
	{
		JFileChooser openFile = new JFileChooser();
        openFile.showOpenDialog(null);
        if(openFile.getSelectedFile() != null )
        {
        	String lp = openFile.getSelectedFile().getAbsolutePath();
        	
        	//Parsing Routine :
        	
        	new Parser(lp,jp,jb);
        	
    			
           	System.out.println("Loading path : "+openFile.getSelectedFile().getAbsolutePath());
        }
        
	}
}

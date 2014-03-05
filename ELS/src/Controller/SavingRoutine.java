package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.*;


public class SavingRoutine {
	public SavingRoutine() throws IOException
	{
		String project_name;

		project_name = JOptionPane.showInputDialog("Please input Project Name : ");
		
		JFileChooser saveFile = new JFileChooser();
        saveFile.showSaveDialog(null);
        if(saveFile.getSelectedFile() != null)
        {	
           String Final_path = saveFile.getSelectedFile().getAbsolutePath();
           if(Final_path == "")
          	  Final_path = "ELS.els";
           if (!Final_path.endsWith(".els")) 
	          Final_path = Final_path + ".els";
	      
         //(use relative path for Unix systems)
         File f = new File(Final_path);
         f.createNewFile();
         
         // Calling File Generator :
         new Generator(f,project_name);
         
           System.out.println("Saving Path : "+Final_path);
        }   
        
	}

}

package controleestoque.util;

//Classe de filtro PDF
import java.io.File;

import javax.swing.filechooser.FileFilter;

class filtroSalvar extends FileFilter {  
    
	public boolean accept(File f) {  
           if (f.isDirectory()) {  
               return true;  
           }  
        
       String filename = f.getName();  
       if (filename.endsWith(".pdf") || filename.endsWith(".PDF")) {
                  return true;  
               } else {  
                  return false;  
               }  
           }  
 
    public String getDescription() {  
             return ".PDF";
    }

       }  
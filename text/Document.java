package text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Document extends Text{
	
	public List<Float> tfXIdf;
	public File file;
	
	
	public Document(File f){
		tfXIdf=new ArrayList<Float>();
		tf=new ArrayList<Float>();
		this.file=f;
		this.fileToStringList();
		tf=new ArrayList <Float>();
	}
	
	private void fileToStringList(){
		
		Scanner sc;
		try {
			sc = new Scanner(new FileInputStream(file));
			sc.useDelimiter("\\s|\\n");
			
			text = new ArrayList<String>();
			
			while(sc.hasNext()) {
				text.add(sc.next());
			}
			sc.close();
		} catch (FileNotFoundException e) {}
	}
	
	public String dislayText() {
        String ret="";
        for(String s : text ) {
            ret+=s+" ";
        }
        ret+="\n";
        return ret;
    }
	
	public void calculTf (Collection c) {
		
		for (String s:c.allWords) {
			tf.add((float)(Collections.frequency(text, s)));
			//Collection.frequency(collection,element) nous permet de trouver le nombre d'occurance d'un element dans une collection (vector, list, map,...)
		}
	}
	
	public void calculTfXIdf(Collection c) {
		
		for(int i=0;i<c.allWords.size();i++) {
			tfXIdf.add(tf.get(i)*c.idf.get(i));
		}
	}

	public String displayTfXIdf() {
		
        String ret="";
        for (Float f:tfXIdf) {
            ret+=f+" ";
        }
        return ret;
    }

    public String displayTf() {
    	
        String ret="";
        for (Float f:tf) {
            ret+=(f+" ");
        }
        return ret;
    }
    
}

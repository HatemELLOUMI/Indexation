package text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Tester {
	
	public static void printFile(File file) {
		
		if (file!=null) {
			
			String s= file.getName()+"\n";
			Scanner sc;
			
			try {
				sc=new Scanner (new FileInputStream(file));
				
				while(sc.hasNext()) {
					s+=sc.nextLine()+"\n";
				}
			
				JOptionPane.showMessageDialog(null, s , "le fichier le plus simulaire au requet est  :", JOptionPane.INFORMATION_MESSAGE);
				sc.close();
				
			} catch (FileNotFoundException e) {
			}
		}
		
		else {
			
			JOptionPane.showMessageDialog(null, "no such file is found  ","Resultat :" , JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	public static void main(String[] args) {
		 
		Collection c = new Collection();
		c.prepareCollection();
		
		for(;;){
			
			Object[] options = {"Show Data Base ?  ", "Search ?"};
			int y ; 
			int x = JOptionPane.showOptionDialog(null, "", "Choose an option", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			
			if ( x==0 ){
				
				 String[] Docs = new String[c.collection.size()];
				 for (int i=0;i<Docs.length;i++) {
				   	 Docs[i]=c.collection.get(i).file.getName();
			   	 }
	        
				 y= JOptionPane.showOptionDialog(null, "","Choose Document",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, Docs, Docs[0]);
		         File f=c.collection.get(x).file;
	
		         Document d=new Document(f);
		         String s="";
		         s+="original text\n";
		         s+=d.dislayText();
		         s+="\n__\n";
		         d.removeStopWords(c.stopWords);
		         s+="without stop words\n";
		         s+=d.dislayText();
		         s+="\n__\n";
		         d.applyPorterAlgorithm();
		         s+="with porter\n";
		         s+=d.dislayText();
		         s+="\n__\n";
		         s+="stopwords\n";
		         s+=c.displayAllWords();
		         s+="\n__\n";
		         s+="idf\n";
		         s+=c.displayIdf();
		         s+="\n__\n";
		         s+="tf\n";
		         d.calculTf(c);
		         s+=d.displayTf();
		         s+="\n__\n";
		         s+="tf * idf\n";
		         d.calculTfXIdf(c);
		         s+=d.displayTfXIdf();
		         JOptionPane.showMessageDialog(null, s,"The file contains :" , JOptionPane.INFORMATION_MESSAGE);
	        
			}
    
			else if ( x==1 ){
				
				String s = (String)JOptionPane.showInputDialog(null,"Search for a word" ,"Chaos Search: ",JOptionPane.PLAIN_MESSAGE,null,null, "");
				Request r=new Request (s);
				r.removeStopWords(c.stopWords);
				r.applyPorterAlgorithm();
				r.calculTf(c);
				printFile(r.searchSimilarFile(c));		
			}
			
			else if (x==-1)
				break ; 
		}
	}
}

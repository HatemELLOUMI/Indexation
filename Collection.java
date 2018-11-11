package text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Collection {
	public List <String> allWords;
	public List<Float> idf;
	public List<Document> collection;
	public Document stopWords;
	public String databasePath;
	
	public Collection() {
		
		idf=new ArrayList <Float>();
		databasePath="database";
		allWords=new ArrayList <String>();
		collection= new ArrayList <Document>();
		int n;
		
		File database=new File(databasePath);
		stopWords=new Document(new File("stopwords.txt"));
		for (File f: database.listFiles()) {
			collection.add(new Document(f));
		}
		for(int i=0; i< collection.size();i++) {
			collection.get(i).removeStopWords(stopWords);
			collection.get(i).applyPorterAlgorithm();
		}
		
	}
	
	public void prepareCollection() {

		searchAllWords();
		calculTfCollection();
		calculIdf();
		calculTfXIdfCollection();
	}
	
	private void searchAllWords () {
		
		for (Text t:collection) {
			for (String s:t.text) {
				if (!allWords.contains(s)) {
					allWords.add(s);
				}
			}
		}
	}
	
	private void calculIdf() {

		int d;
		for (int i=0;i<allWords.size();i++) {
			d=0;
			for (Text t:collection)
				if (t.tf.get(i)!=0)
					d++;
			idf.add( (float) Math.log10 (collection.size() / d) );
		}
	}
	
	private void calculTfCollection() {
		
		for (Text t:collection) {
			t.calculTf(this);
		}
	}
	
	private void calculTfXIdfCollection() {
		
		for (Document t:collection) {
			t.calculTfXIdf(this);
		}
	}
	
	public String displayAllWords() {
		
        String ret="";
        for (String s:allWords) {
            ret+=(s+" ");
        }

        return ret;
    }
	
	public String displayIdf() {
		
        String ret="";
        for (Float f:idf) {
            ret+=(f+" ");
        }
        return ret;
    }
	
	public void displayTf() {
		
		for (Document t:collection) {
			t.displayTf();
		}
	}
	
	public void displayTfXIdf() {
		
		for (Document t:collection) {
			t.displayTfXIdf();
		}
	}
}

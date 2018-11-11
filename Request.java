package text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Request extends Text{

	public Request(String s) {
		
		tf=new ArrayList<Float>();
		text=new ArrayList <String>();
		Scanner sc=new Scanner(s);
		sc.useDelimiter("\\s|\\n");
		while (sc.hasNext()) {
			text.add(sc.next());
		}
	}

	public void calculTf(Collection c) {
		
		for (int i=0;i<c.allWords.size();i++) {
			if (text.contains(c.allWords.get(i)))
				tf.add(0.5f);
			else
				tf.add(0f);
		}
	}

	public static float calculSimilarity(List <Float> A, List <Float> B) {
		
		float x=0,y=0,z=0;
		
		for (int i=0;i<A.size();i++) {
			x+=A.get(i)*B.get(i);
			y+=Math.pow(A.get(i), 2);
			z+=Math.pow(B.get(i), 2);
		}
		
		return (float)Math.abs((x/(Math.sqrt(y)*Math.sqrt(z))));
	}
	
	 
	public File searchSimilarFile(Collection c) {
		
		float max=0,x;
		File file=null;
		
		for (Document t:c.collection) {
			x=calculSimilarity(tf, t.tfXIdf);
			if (x>max) {
				max=x;
				file=t.file;
			}
		}
		return file;
	}
}

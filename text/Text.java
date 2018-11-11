package text;

import java.util.List;

public abstract class Text {

	public List <String> text;
	public List <Float> tf;
	
	protected void applyPorterAlgorithm (){
		
		Porter porterStemmer=new Porter();
		for(int i=0;i<text.size();i++) {
			text.set(i,porterStemmer.stripAffixes(text.get(i)));
		}
	}
	
	protected void removeStopWords(Text stopWords) {
		
		for (int i=0;i<text.size();i++) {
			if (stopWords.text.contains(text.get(i))) {
				
				text.remove(i);
				i--;
			}
		}
	}
	
	protected abstract void calculTf(Collection c);
}

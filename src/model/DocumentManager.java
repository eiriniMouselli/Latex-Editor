package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class DocumentManager {
	private Map<String,Document>  prototypesMap;
	
	
	public DocumentManager(){
		prototypesMap= new  HashMap<String,Document>();
	}
	
	public void dynamicallyLoadTemplates(String fileName) {
		String current_line="";
		String contents="";
		String key="";
		try {
			BufferedReader documentReader = new BufferedReader(
					new FileReader (fileName)
					);
			
			current_line= documentReader.readLine();
			key=current_line;
			 while ((current_line =documentReader.readLine()) != null) {
				 contents+=current_line + '\n';
			 }
			
			prototypesMap.put(key,new Document(key,contents.trim()));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	public Document createDocument(String templateID) {	
		Document tmpDocument = new Document(templateID,prototypesMap.get(templateID).getContents());
		Document newDocument = tmpDocument.clone();
		return newDocument;
	}

	public void dynamicallyLoad(String openPath){
		dynamicallyLoadTemplates(openPath +'/'+"article-template.tex");
		dynamicallyLoadTemplates(openPath +'/'+"book-template.tex");
		dynamicallyLoadTemplates(openPath +'/'+"letter-template.tex");
		dynamicallyLoadTemplates(openPath +'/'+"report-template.tex");
		dynamicallyLoadTemplates(openPath +'/'+"empty-template.tex");
	}


}

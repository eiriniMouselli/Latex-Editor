package model.strategies;
import model.*;
import view.*;
import controller.*;

import java.util.ArrayList;
import java.io.*;
import java.nio.file.*; 
public class StableVersionsStrategy implements VersionsStrategy {
	private ArrayList <Document> versionsHistory=new ArrayList <Document>() ;
	private String savePath;
	private int counter,index=0;
	
	public StableVersionsStrategy(){}
	

	public void putVersion(Document document){
		versionsHistory.add(document);
		createFile(document);
	}
	
	public void createFile(Document document){

		String folder= (document.getTemplateID() + document.getAuthor());
		String filename=null;
		FileOutputStream fileOut=null;
		ObjectOutputStream out=null;
		
		File file = new File(savePath + '/' + folder);
	    if (!file.exists()) {
	    	file.mkdir();
	    	counter =0;
        }
	        
		try {
				document.setVersionID(counter);
				filename = String.valueOf(counter);
				counter++;
		        fileOut = new FileOutputStream(savePath+'/' + folder +"/" + filename + ".ser");			
		        out = new ObjectOutputStream(fileOut);
		        out.writeObject(document); 
		        out.close();
		        fileOut.close();
		        index++;
		       
	      } catch (IOException j) {
	         j.printStackTrace();
	      }
	}
	
	public Document getVersion(){
		if(versionsHistory.size()>0) {
			return(versionsHistory.get(versionsHistory.size()-1));
		}
		return null;
	}
	public void setEntireHistory(ArrayList<Document> historyList){
		versionsHistory=historyList;
	}
	public ArrayList<Document> getEntireHistory(){
		return(versionsHistory);
	}
	public void removeVersion(){
		if(versionsHistory.size()>0) {

			String folder= (versionsHistory.get(versionsHistory.size()-1).getTemplateID() + versionsHistory.get(versionsHistory.size()-1).getAuthor());
			String filename=null;
			filename=String.valueOf(versionsHistory.get((versionsHistory.size()-1)).getVersionID());
			
			try {
				Files.deleteIfExists(Paths.get(savePath + '/'+ folder+ '/' + filename + ".ser"));
				counter--;
				versionsHistory.remove(versionsHistory.size()-1);	
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
		}
	
	}
	public void setCounter(int counter){
		this.counter=counter;
	}
	public int getCounter(){
		return counter;
	}
	public String getSavePath(){
			return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	public int getIndex(){
		return index;
		
	}public void setIndex(int index){
		this.index=index;
	}
	
}

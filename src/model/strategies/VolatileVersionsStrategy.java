package model.strategies;
import model.*;

import java.util.ArrayList;

public class VolatileVersionsStrategy implements VersionsStrategy {
	
	private ArrayList<Document> versionsHistory;
	private String savePath;
	private int index,counter;
	public VolatileVersionsStrategy(){
		versionsHistory = new ArrayList<Document>();
	}
	
	public void putVersion(Document document){
		versionsHistory.add(document);
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
			versionsHistory.remove(versionsHistory.size()-1);
		}
		
	}
	public void setSavePath(String savePath){
		this.savePath=savePath;
	}	
	public String getSavePath(){
		return savePath;
	}
	public int getIndex(){
		return index;
		
	}public void setIndex(int index){
		this.index=index;
	}
	public void setCounter(int counter){
		this.counter=counter;
	}
	public int getCounter(){
		return counter;
	}
}

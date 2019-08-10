package model;

import java.util.ArrayList;

import model.strategies.VersionsStrategy;
import view.*;
public class VersionsManager {
	private boolean enabled;
	private Document currentDocument;
	private VersionsStrategy strategy;
	
	
	

	public VersionsManager(VersionsStrategy strategy){
		this.strategy=strategy;
		this.currentDocument=new Document("Empty","");
	}
	
	public boolean isEnabled() {
		return(enabled);
	}
	
	public void enable() {
		enabled=true;
	}
	
	public void disable() {
		enabled=false;
	}
	
	public void setStrategy(VersionsStrategy strategy) {
		this.strategy=strategy;
	}
	
	public void setCurrentVersion(Document document) {
		if(enabled && currentDocument!=null){
			strategy.putVersion(currentDocument);
		}	
		currentDocument=document.clone();
		currentDocument.setAuthor(document.getAuthor());
		currentDocument.setCopyright(document.getCopyright());
		
		}
	
	public Document getPreviousVersion() {
		if(enabled){
			return(strategy.getVersion());
		}
		return null;
	}
	
	public void rollbackToPreviousVersion() {
		if(enabled){
			strategy.removeVersion();
			currentDocument=strategy.getVersion();
			
		}
	}

	public Document getCurrentDocument() {
		return currentDocument;
	}
	public void setCurrentDocument(){
		currentDocument=null;
	}
	public VersionsStrategy getStrategy() {
		
		return strategy;
	}

	
	



	
}

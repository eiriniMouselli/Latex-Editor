package controller.commands;

import view.LatexEditorView;
import model.*;

public class CreateCommand implements Command{

	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private Document newDocument;
	private LatexEditorView window;

	public CreateCommand(LatexEditorView window){

		this.window=window;
		this.documentManager=window.getDocumentManager();
		this.versionsManager=window.getVersionsManager();
	}
	
	public void execute(){
		
		newDocument=documentManager.createDocument(window.getTemplateID());
		versionsManager.setCurrentDocument();
		versionsManager.setCurrentVersion(newDocument);
		if(versionsManager.getStrategy()!=null){
			versionsManager.getStrategy().getEntireHistory().clear();
		}
	}


}

package controller.commands;

import view.LatexEditorView;
import model.Document;
import model.DocumentManager;
import model.VersionsManager;

public class AddLatexCommand implements Command {
	
	private LatexEditorView window;
	
	public  AddLatexCommand(LatexEditorView window){
		this.window=window;
		
	}
	public void execute() {
		
		Document newDoc =window.getVersionsManager().getCurrentDocument();
		window.getTextArea().insert(window.getLatexCommand(), window.getTextArea().getCaretPosition());
		newDoc.setContents(window.getTextArea().getText());
		window.getVersionsManager().setCurrentVersion(newDoc);

	}
}

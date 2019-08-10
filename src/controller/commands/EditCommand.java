package controller.commands;

import view.LatexEditorView;
import model.Document;
import model.DocumentManager;
import model.VersionsManager;

public class EditCommand implements Command {
	private LatexEditorView window;
	
	public EditCommand(LatexEditorView window) {
		this.window = window;
	}
	 public void execute() {
		Document newDoc =window.getVersionsManager().getCurrentDocument();
		newDoc.setContents(window.getTextArea().getText());
		window.getVersionsManager().setCurrentVersion(newDoc);
		
	 }
}

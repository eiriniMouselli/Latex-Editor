package controller.commands;

import view.LatexEditorView;
import model.DocumentManager;
import model.VersionsManager;

public class RollbackToPreviousVersionCommand implements Command{
	
	private LatexEditorView window;
	
	public RollbackToPreviousVersionCommand(LatexEditorView window) {
		this.window=window;
	}
	public void execute() {
		window.getVersionsManager().rollbackToPreviousVersion();
		if(window.getVersionsManager().getCurrentDocument()!=null) {
			window.getTextArea().setText(window.getVersionsManager().getCurrentDocument().getContents());
		}
	}
  
}

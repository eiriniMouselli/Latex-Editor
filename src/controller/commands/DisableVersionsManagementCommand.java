package controller.commands;

import view.LatexEditorView;
import model.DocumentManager;
import model.VersionsManager;

public class DisableVersionsManagementCommand implements Command {
	private LatexEditorView window;	

	public DisableVersionsManagementCommand(LatexEditorView window) {
		this.window=window;
	}

	public void execute() {
		if(window.getVersionsManager().isEnabled()){
			window.getVersionsManager().disable();
		}
		window.getRdbtnStable().setEnabled(false);
		window.getRdbtnVolatile().setEnabled(false);
	
	}

}

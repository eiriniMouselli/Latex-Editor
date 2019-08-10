package controller.commands;

import java.util.ArrayList;

import view.LatexEditorView;
import model.DocumentManager;
import model.VersionsManager;

public class EnableVersionsManagementCommand implements Command{
	private LatexEditorView window;
	
	public EnableVersionsManagementCommand(LatexEditorView window) {
		this.window=window;
	}

	public void execute() {
		if(!window.getVersionsManager().isEnabled()){
			window.getVersionsManager().enable();
		}
		window.getRdbtnStable().setEnabled(true);
		window.getRdbtnVolatile().setEnabled(true);
		if(window.getChoice().equals("Volatile")){
			window.getRdbtnVolatile().setSelected(true);
		}else{
			window.getRdbtnStable().setSelected(true);
		}
		
		window.getVersionsManager().setStrategy(window.getVersionsFactory().createStrategy(window.getChoice()));
		window.getVersionsManager().getStrategy().setSavePath(window.getSavePath());
		
		
	}
	
}

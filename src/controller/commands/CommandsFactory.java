package controller.commands;

import view.LatexEditorView;
import model.DocumentManager;
import model.VersionsManager;

public class CommandsFactory {
	
	private LatexEditorView window;
	
	public CommandsFactory(LatexEditorView window){
		this.window=window;
	}
	
	public Command createCommand(String commandChoice) {
		if(commandChoice.equals("AddLatex")) {
			return(new AddLatexCommand(window));
		}else if (commandChoice.equals("Create")) {
			return(new CreateCommand(window));
		}else if (commandChoice.equals("ChangeVersionsStrategy")) {
			return(new ChangeVersionsStrategyCommand(window));
		}else if (commandChoice.equals("Edit")) {
			return(new EditCommand(window));
		}else if (commandChoice.equals("DisableVersionsManagement")) {
			return(new DisableVersionsManagementCommand(window));
		}else if (commandChoice.equals("EnableVersionsManagement")) {
			return(new EnableVersionsManagementCommand(window));
		}else if (commandChoice.equals("Load")) {
			return(new LoadCommand(window));
		}else if (commandChoice.equals("RollbackToPreviousVersion")) {
			return(new RollbackToPreviousVersionCommand(window));
		}else if (commandChoice.equals("Save")) {
			return(new SaveCommand(window));
		}
		return(null);
	}
    
}

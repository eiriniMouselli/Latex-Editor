package controller;
import java.util.HashMap;

import view.LatexEditorView;
import controller.commands.*;
import model.*;

public class LatexEditorController {

	private LatexEditorView window;
	private HashMap <String,Command> commandsMap=new HashMap <String,Command>();
	private CommandsFactory factory;
	
	
	public LatexEditorController(LatexEditorView window){
		this.window=window;
		factory=new CommandsFactory(window);
		
		commandsMap.put("AddLatex",factory.createCommand("AddLatex"));
		commandsMap.put("ChangeVersionsStrategy",factory.createCommand("ChangeVersionsStrategy"));
		commandsMap.put("Create",factory.createCommand("Create"));
		commandsMap.put("DisableVersionsManagement",factory.createCommand("DisableVersionsManagement"));
		commandsMap.put("EnableVersionsManagement",factory.createCommand("EnableVersionsManagement"));
		commandsMap.put("Edit",factory.createCommand("Edit"));
		commandsMap.put("Load",factory.createCommand("Load"));
		commandsMap.put("Save",factory.createCommand("Save"));
		commandsMap.put("RollbackToPreviousVersion",factory.createCommand("RollbackToPreviousVersion"));
		
	}
	
	public void enact(String command) {
		commandsMap.get(command).execute();
	}
}

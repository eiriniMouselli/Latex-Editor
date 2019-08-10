package controller.commands;

import view.LatexEditorView;
import model.DocumentManager;
import model.VersionsManager;
import model.strategies.*;
public class ChangeVersionsStrategyCommand implements Command{
	private LatexEditorView window;
	private VolatileVersionsStrategy vol;
	private StableVersionsStrategy stab;
	private int c;
	
	public ChangeVersionsStrategyCommand(LatexEditorView window) {
		this.window=window;
		this.vol=new VolatileVersionsStrategy();
		this.stab=new StableVersionsStrategy();
	}

	public void execute() {
		if(window.getChoice().equals("Stable")){
			vol.setEntireHistory(window.getVersionsManager().getStrategy().getEntireHistory());
			vol.setSavePath(window.getVersionsManager().getStrategy().getSavePath());
			
			vol.setIndex(window.getVersionsManager().getStrategy().getIndex());
			window.getVersionsManager().setStrategy(vol);
			window.setChoice("Volatile");
		}else if(window.getChoice().equals("Volatile")) {
			stab.setEntireHistory(window.getVersionsManager().getStrategy().getEntireHistory());
			stab.setSavePath(window.getVersionsManager().getStrategy().getSavePath());
			
			c=stab.getIndex();
			for(int i=c;i<window.getVersionsManager().getStrategy().getEntireHistory().size();i++){
				stab.createFile(stab.getEntireHistory().get(i));
				
			}
			window.getVersionsManager().setStrategy(stab);
			window.setChoice("Stable");
			
		}
		
	}

}

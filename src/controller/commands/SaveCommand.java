package controller.commands;
import model.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import view.LatexEditorView;
import model.DocumentManager;
import model.VersionsManager;

public class SaveCommand implements Command{
	private LatexEditorView window;
	private Document document;
	public SaveCommand(LatexEditorView window) {
		this.window=window;
	}
	
	public void execute() {
		document=window.getVersionsManager().getCurrentDocument();
		
		String folder= (document.getTemplateID() + document.getAuthor());
		String filename=null;
		FileOutputStream fileOut=null;
		ObjectOutputStream out=null;
		document.setVersionID(window.getVersionsManager().getStrategy().getEntireHistory().size());
	
		File file = new File(window.getVersionsManager().getStrategy().getSavePath() + '/' + folder);
	        if (!file.exists()) {
	            file.mkdir();
	            window.getVersionsManager().getStrategy().setCounter(0);
	        }
	        
		try {
				filename =String.valueOf(window.getVersionsManager().getStrategy().getCounter());
				document.setVersionID(window.getVersionsManager().getStrategy().getCounter());
				 window.getVersionsManager().getStrategy().setCounter(window.getVersionsManager().getStrategy().getCounter()+1);
		        fileOut = new FileOutputStream(window.getVersionsManager().getStrategy().getSavePath()+'/' + folder +"/" + filename + ".ser");			// change path
		        out = new ObjectOutputStream(fileOut);
		        out.writeObject(document); 
		        out.close();
		        fileOut.close();
		        window.getVersionsManager().getStrategy().setIndex(window.getVersionsManager().getStrategy().getIndex()+1);
	      } catch (IOException j) {
	         j.printStackTrace();
	      }
	}
}

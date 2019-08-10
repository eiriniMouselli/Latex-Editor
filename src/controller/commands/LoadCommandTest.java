package controller.commands;

import static org.junit.Assert.*;
import model.Document;

import org.junit.Assert;
import org.junit.Test;

import view.LatexEditorView;

public class LoadCommandTest {

	private LatexEditorView window;	
	private EnableVersionsManagementCommand enableCom;
	private CreateCommand createCom;
	private SaveCommand saveCom;
	private LoadCommand loadCom;
	
	
	
	public LoadCommandTest(){
		window= new LatexEditorView();
		enableCom= new EnableVersionsManagementCommand(window);
		saveCom= new SaveCommand(window);
		loadCom=new LoadCommand(window);
		createCom = new CreateCommand(window);
		
	}
	
	
	@Test
	public void test() {
		window.setChoice("Stable");
		window.setTemplateID("Empty");
		String openPath="/usr/home/students/stud15/cse53003/workspace/Latex/tex-templates";
		window.setLoadPath("/usr/home/students/stud15/cse53003/Desktop/documents/Emptynull");
		window.setSavePath("/usr/home/students/stud15/cse53003/Desktop/documents");
		window.getDocumentManager().dynamicallyLoad(openPath);
		enableCom.execute();
		createCom.execute();
		saveCom.execute();
		window.setTemplateID("Report");
		createCom.execute();
		saveCom.execute();
		loadCom.execute();
		Assert.assertEquals("Empty",window.getVersionsManager().getCurrentDocument().getTemplateID());
	}

}

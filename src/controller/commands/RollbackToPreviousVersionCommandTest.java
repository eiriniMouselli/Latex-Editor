package controller.commands;

import static org.junit.Assert.*;
import model.Document;

import org.junit.Assert;
import org.junit.Test;

import view.LatexEditorView;

public class RollbackToPreviousVersionCommandTest {

	private LatexEditorView window;
	private EnableVersionsManagementCommand enableCom;
	private EditCommand editCom;
	private RollbackToPreviousVersionCommand rolCom;
	private Document doc;
	
	public RollbackToPreviousVersionCommandTest(){
		window= new LatexEditorView();
		enableCom= new EnableVersionsManagementCommand(window);
		editCom= new EditCommand(window);
		rolCom= new RollbackToPreviousVersionCommand(window);
		doc= new Document("Empty","");
	}
	
	
	
	@Test
	public void test() {
		
		window.setChoice("Volatile");
		enableCom.execute();
		window.getVersionsManager().setCurrentVersion(doc);
		window.setTextArea("Test1");
		editCom.execute();
		window.setTextArea("Test2");
		editCom.execute();
		rolCom.execute();
		Assert.assertEquals("Test1",window.getVersionsManager().getCurrentDocument().getContents());
		
	}

}

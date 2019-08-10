package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import view.LatexEditorView;

public class DisableVersionsManagementCommandTest {
	
	private LatexEditorView window;
	private EnableVersionsManagementCommand enableCom;
	private DisableVersionsManagementCommand disableCom;
	private EditCommand editCom;
	
	public DisableVersionsManagementCommandTest(){
		window= new LatexEditorView();
		enableCom= new EnableVersionsManagementCommand(window);
		disableCom= new DisableVersionsManagementCommand(window);
		editCom= new EditCommand(window);
	}
	
	
	
	
	@Test
	public void test() {
		window.setChoice("Volatile");
		enableCom.execute();
		disableCom.execute();
		window.setTextArea("TEST");
		editCom.execute();
		Assert.assertEquals(0,window.getVersionsManager().getStrategy().getEntireHistory().size());
		
		
	}

}

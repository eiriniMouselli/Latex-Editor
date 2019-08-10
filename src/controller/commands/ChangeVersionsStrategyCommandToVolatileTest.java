package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import view.LatexEditorView;

public class ChangeVersionsStrategyCommandToVolatileTest {

	private LatexEditorView window;
	private EnableVersionsManagementCommand enableCom;
	private ChangeVersionsStrategyCommand changeCom;
	
	public  ChangeVersionsStrategyCommandToVolatileTest(){
		window= new LatexEditorView();
		enableCom= new EnableVersionsManagementCommand(window);
		changeCom= new ChangeVersionsStrategyCommand (window);
		
	}
	
	
	
	@Test
	public void test() {
		window.setChoice("Stable");
		enableCom.execute();
		changeCom.execute();
		Assert.assertEquals("Volatile",window.getChoice());
		
	}

}

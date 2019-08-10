package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import view.LatexEditorView;

public class ChangeVersionsStrategyCommandToStableTest {

	private LatexEditorView window;
	private EnableVersionsManagementCommand enableCom;
	private ChangeVersionsStrategyCommand changeCom;
	
	public  ChangeVersionsStrategyCommandToStableTest(){
		window= new LatexEditorView();
		enableCom= new EnableVersionsManagementCommand(window);
		changeCom= new ChangeVersionsStrategyCommand (window);
		
	}
	
	
	
	@Test
	public void test() {
		window.setChoice("Volatile");
		enableCom.execute();
		changeCom.execute();
		Assert.assertEquals("Stable",window.getChoice());
		
	}

}

package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import model.*;
import view.LatexEditorView;

public class EditCommandTest {

	private EditCommand editCom;
	private Document doc;
	private LatexEditorView window;
	
	public EditCommandTest(){
		window = new LatexEditorView();
		editCom = new EditCommand(window);
		doc = new Document("Empty","");
		
	}
	@Test
	public void test() {
		window.getVersionsManager().setCurrentVersion(doc);
		window.setTextArea("TEST");
		editCom.execute();
		Assert.assertEquals("","TEST",window.getVersionsManager().getCurrentDocument().getContents());
	}

}

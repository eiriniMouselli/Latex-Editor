package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import view.LatexEditorView;

public class AddLatexCommandTestChapter {
	private CreateCommand createCom;
	private AddLatexCommand addCom;
	private LatexEditorView view;
	
	
	public AddLatexCommandTestChapter () {
		view=new LatexEditorView();
		view.setTemplateID("Empty");
		addCom=new AddLatexCommand(view);
		createCom=new CreateCommand(view);
		view.setLatexCommand("\\chapter{..}");

	}
	
	@Test
	public void test() {
		createCom.execute();
		addCom.execute();
		Assert.assertEquals("","\\chapter{..}",view.getVersionsManager().getCurrentDocument().getContents());
	}

}

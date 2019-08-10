package controller.commands;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import view.LatexEditorView;
import org.junit.Test;

public class AddLatexCommandTestSection {

	private CreateCommand createCom;
	private AddLatexCommand addCom;
	private LatexEditorView view;
		
	public AddLatexCommandTestSection () {
		view=new LatexEditorView();
		view.setTemplateID("Empty");
		addCom=new AddLatexCommand(view);
		createCom=new CreateCommand(view);
		view.setLatexCommand("\\section{}");
		
		}
		
		
		public void test() {
			createCom.execute();
			addCom.execute();
			Assert.assertEquals("","\\section{}",view.getVersionsManager().getCurrentDocument().getContents());
		}
}

	



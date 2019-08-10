package controller.commands;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import view.LatexEditorView;
import org.junit.Test;

public class AddLatexCommandTestSubsubsection {

	private CreateCommand createCom;
	private AddLatexCommand addCom;
	private LatexEditorView view;
		
	public AddLatexCommandTestSubsubsection () {
		view=new LatexEditorView();
		view.setTemplateID("Empty");
		addCom=new AddLatexCommand(view);
		createCom=new CreateCommand(view);
		view.setLatexCommand("\\subsubsection{}");
		
		}
		
		
		public void test() {
			createCom.execute();
			addCom.execute();
			Assert.assertEquals("","\\subsubsection{}",view.getVersionsManager().getCurrentDocument().getContents());
		}
}

	



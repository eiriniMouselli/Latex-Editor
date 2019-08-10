package controller.commands;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import view.LatexEditorView;
import org.junit.Test;

public class AddLatexCommandTestItemize {

	private CreateCommand createCom;
	private AddLatexCommand addCom;
	private LatexEditorView view;
		
	public AddLatexCommandTestItemize () {
		view=new LatexEditorView();
		view.setTemplateID("Empty");
		addCom=new AddLatexCommand(view);
		createCom=new CreateCommand(view);
		view.setLatexCommand("\\begin{itemize} \n\\item ...\n\\item ...\n\\end{itemize}");
		
		}
		
		
		public void test() {
			createCom.execute();
			addCom.execute();
			Assert.assertEquals("","\\begin{itemize} \n\\item ...\n\\item ...\n\\end{itemize}",view.getVersionsManager().getCurrentDocument().getContents());
		}
}

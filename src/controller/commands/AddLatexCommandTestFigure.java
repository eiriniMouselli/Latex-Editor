package controller.commands;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import view.LatexEditorView;
import org.junit.Test;

public class AddLatexCommandTestFigure {

	private CreateCommand createCom;
	private AddLatexCommand addCom;
	private LatexEditorView view;
		
	public AddLatexCommandTestFigure () {
		view=new LatexEditorView();
		view.setTemplateID("Empty");
		addCom=new AddLatexCommand(view);
		createCom=new CreateCommand(view);
		view.setLatexCommand("\\begin{figure}\\includegraphics[width=...,height=...]{...} \n \\caption{....}\\label{...} \n \\end{figure}");
		
		}
		
		
		public void test() {
			createCom.execute();
			addCom.execute();
			Assert.assertEquals("","\\begin{figure}\\includegraphics[width=...,height=...]{...} \n \\caption{....}\\label{...} \n \\end{figure}",view.getVersionsManager().getCurrentDocument().getContents());
		}
}

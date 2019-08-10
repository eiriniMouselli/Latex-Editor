package controller.commands;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import view.LatexEditorView;
import org.junit.Test;

public class AddLatexCommandTestTable {

	private CreateCommand createCom;
	private AddLatexCommand addCom;
	private LatexEditorView view;
		
	public AddLatexCommandTestTable () {
		view=new LatexEditorView();
		view.setTemplateID("Empty");
		addCom=new AddLatexCommand(view);
		createCom=new CreateCommand(view);
		view.setLatexCommand("\\begin{table}\n\\caption{....}\\label{...} \n \\begin{tabular}{|c|c|c|} \n \\hline \n... &...&...\\ \n ... &...&...\\ \n ... &...&...\\ \n\\hline \n\\end{tabular} \n\\end{table}");
		
		}
		
		
		public void test() {
			createCom.execute();
			addCom.execute();
			Assert.assertEquals("","\\begin{table}\n\\caption{....}\\label{...} \n \\begin{tabular}{|c|c|c|} \n \\hline \n... &...&...\\ \n ... &...&...\\ \n ... &...&...\\ \n\\hline \n\\end{tabular} \n\\end{table}",view.getVersionsManager().getCurrentDocument().getContents());
		}
}

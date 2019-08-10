package controller.commands;
import view.*;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class CreateCommandTestReport {

	CreateCommand createCom;
	LatexEditorView view;	
		public  CreateCommandTestReport () {
			view=new LatexEditorView();
			view.setTemplateID("Report");
			createCom=new CreateCommand(view);
			}
			@Test
			public void test() {
				createCom.execute();
				Assert.assertEquals("","\\documentclass[11pt,a4paper]{report}\n" + 
						"\n" + 
						"\\begin{document}\n" + 
						"\\title{Report Template: How to Structure a LaTeX Document}\n" + 
						"\\author{Author1 \\and Author2 \\and ...}\n" + 
						"\\date{\\today}\n" + 
						"\\maketitle\n" + 
						"\n" + 
						"\\begin{abstract}\n" + 
						"Your abstract goes here...\n" + 
						"...\n" + 
						"\\end{abstract}\n" + 
						"\n" + 
						"\\chapter{Introduction}\n" + 
						"\\section{Section Title 1}\n" + 
						"\\section{Section Title 2}\n" + 
						"\\section{Section Title.....}\n" + 
						"\n" + 
						"\\chapter{....}\n" + 
						"\n" + 
						"\\chapter{Conclusion}\n" + 
						"\n" + 
						"\n" + 
						"\\chapter*{References}\n" + 
						"\n" + 
						"\\end{document}",view.getVersionsManager().getCurrentDocument().getContents());
				
			}
}

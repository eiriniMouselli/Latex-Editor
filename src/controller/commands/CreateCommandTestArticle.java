package controller.commands;
import org.junit.Assert;
import org.junit.Test;

import view.*;

public class CreateCommandTestArticle {

	private CreateCommand createCom;
	private LatexEditorView view;
	public CreateCommandTestArticle () {
		view=new LatexEditorView();
		view.setTemplateID("Article");
		createCom=new CreateCommand(view);
		}
		@Test
		public void test() {
			createCom.execute();
			Assert.assertEquals("","\\documentclass[11pt,twocolumn,a4paper]{article}\n" + 
					"\n" + 
					"\\begin{document}\n" + 
					"\\title{Article: How to Structure a LaTeX Document}\n" + 
					"\\author{Author1 \\and Author2 \\and ...}\n" + 
					"\\date{\\today}\n" + 
					"\n" + 
					"\\maketitle\n" + 
					"\n" + 
					"\\section{Section Title 1}\n" + 
					"\n" + 
					"\\section{Section Title 2}\n" + 
					"\n" + 
					"\\section{Section Title.....}\n" + 
					"\n" + 
					"\\section{Conclusion}\n" + 
					"\n" + 
					"\\section*{References}\n" + 
					"\n" + 
					"\\end{document}",view.getVersionsManager().getCurrentDocument().getContents());
			
		}

	}
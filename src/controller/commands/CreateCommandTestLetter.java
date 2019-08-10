package controller.commands;

import static org.junit.Assert.*;
import view.*;

import org.junit.Assert;
import org.junit.Test;
public class CreateCommandTestLetter {
	CreateCommand createCom;
	LatexEditorView view;	
		public  CreateCommandTestLetter () {
			view=new LatexEditorView();
			view.setTemplateID("Letter");
			createCom=new CreateCommand(view);
			}
			@Test
			public void test() {
				createCom.execute();
				Assert.assertEquals("","\\documentclass{letter}\n" + 
						"\\usepackage{hyperref}\n" + 
						"\\signature{Sender's Name}\n" + 
						"\\address{Sender's address...}\n" + 
						"\\begin{document}\n" + 
						"\n" + 
						"\\begin{letter}{Destination address....}\n" + 
						"\\opening{Dear Sir or Madam:}\n" + 
						"\n" + 
						"I am writing to you .......\n" + 
						"\n" + 
						"\n" + 
						"\\closing{Yours Faithfully,}\n" + 
						"\n" + 
						"\\ps\n" + 
						"\n" + 
						"P.S. text .....\n" + 
						"\n" + 
						"\\encl{Copyright permission form}\n" + 
						"\n" + 
						"\\end{letter}\n" + 
						"\\end{document}",view.getVersionsManager().getCurrentDocument().getContents());
				
			}

}


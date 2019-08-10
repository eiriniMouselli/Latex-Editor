package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import view.LatexEditorView;

public class CreateCommandTestEmpty {

CreateCommand createCom;
LatexEditorView view;	
	public  CreateCommandTestEmpty () {
		view=new LatexEditorView();
		view.setTemplateID("Empty");
		createCom=new CreateCommand(view);
		}
		@Test
		public void test() {
			createCom.execute();
			Assert.assertEquals("","",view.getVersionsManager().getCurrentDocument().getContents());
			
		}

	}


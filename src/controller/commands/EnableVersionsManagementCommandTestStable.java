package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import view.LatexEditorView;

public class EnableVersionsManagementCommandTestStable {

	private LatexEditorView view;
	private EnableVersionsManagementCommand enableCom;
	private String contents;
	private CreateCommand createCom;
	private EditCommand editCom;
	
	public EnableVersionsManagementCommandTestStable(){
		view=new LatexEditorView();
		view.setTemplateID("Article");
		enableCom = new EnableVersionsManagementCommand(view);
		createCom = new CreateCommand(view);
		editCom = new EditCommand(view);
		view.setChoice("Stable");
		view.setLatexCommand("\\section{}");
	}
	public void test() {
		createCom.execute();
		contents=view.getVersionsManager().getCurrentDocument().getContents();
		editCom.execute();
		enableCom.execute();
		Assert.assertEquals("",contents,view.getVersionsManager().getCurrentDocument().getContents());
	}

}

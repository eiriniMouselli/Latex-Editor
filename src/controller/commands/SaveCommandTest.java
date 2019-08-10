package controller.commands;

import static org.junit.Assert.*;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import model.Document;

import org.junit.Assert;
import org.junit.Test;

import view.LatexEditorView;

public class SaveCommandTest {

	private LatexEditorView window;	
	private EnableVersionsManagementCommand enableCom;
	private EditCommand editCom;
	private Document doc;
	private SaveCommand saveCom;
	private FileInputStream file;
	private ObjectInputStream in;
	public SaveCommandTest(){
		window= new LatexEditorView();
		enableCom= new EnableVersionsManagementCommand(window);
		editCom= new EditCommand(window);
		saveCom= new SaveCommand(window);
		doc= new Document("Empty","Test");
	}
	
	
	
	
	@Test
	public void test() throws IOException, ClassNotFoundException {
		window.setChoice("Volatile");
		window.setSavePath("/usr/home/students/stud15/cse53003/workspace/Latex/saveTest");
		window.setLoadPath("/usr/home/students/stud15/cse53003/workspace/Latex/saveTest/Emptynull");
		enableCom.execute();
		window.getVersionsManager().setCurrentVersion(doc);
		saveCom.execute();
		try{   
	         file = new FileInputStream(window.getLoadPath()+'/' +"0.ser"); 
	         in = new ObjectInputStream(file);
	         doc = (Document)in.readObject();
		} catch (EOFException e) {
			
		}
		Assert.assertEquals(doc.getContents(),window.getVersionsManager().getCurrentDocument().getContents());
		
		
		
	}

}

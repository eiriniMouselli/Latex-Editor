package controller.commands;

import view.LatexEditorView;
import model.DocumentManager;
import model.VersionsManager;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import model.*;
public class LoadCommand implements Command {

	private LatexEditorView window;
	private Document doc;
	public LoadCommand(LatexEditorView window) {
		this.window=window;
	}
	public void execute() {
		ArrayList <Document> temp_array=new ArrayList <Document>();
		
		if(window.getVersionsManager().getStrategy()!=null){
			window.getVersionsManager().getStrategy().getEntireHistory().clear();
		}
		int counter=0;
		FileInputStream file;
		ObjectInputStream in;
		Document document = (window.getVersionsManager().getCurrentDocument());
		while(true){
			 try{   
		         file = new FileInputStream(window.getLoadPath()+'/' +String.valueOf(counter) +".ser"); 
		         in = new ObjectInputStream(file);
		         doc = (Document)in.readObject();
		         temp_array.add(doc);
				 in.close(); 
		         file.close(); 
		         if (doc!=null){
		            document = doc;
		         }
				 counter++;
				 
			} catch (EOFException e) {
				break;
			}catch(IOException ex){ 
		        	
		            System.out.println("IOException is caught");
		            if(document!=null){
		            	
		            	window.getVersionsManager().setCurrentVersion(document);
		            }
		            for(int i=0; i<temp_array.size()-1;i++){
		            	if(temp_array.get(i).getVersionID()==temp_array.get(i+1).getVersionID()){
		            		temp_array.remove(i+1);
		            	}
		            }
		            window.getVersionsManager().getStrategy().setEntireHistory(temp_array);
		            
					 if(window.getVersionsManager().getCurrentDocument()!=null) {
						window.getTextArea().setText(window.getVersionsManager().getCurrentDocument().getContents());
					}
		            break;
		         
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}	
		}
			
	}
}

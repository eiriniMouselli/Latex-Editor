//Created by Eirini Mouselli, Eftihia Kiafa and Grigoria Nikita for the Software Engineering course
//(MYY803),Department of Computer Science and Engineering, University of Ioannina.

package view;
import controller.*;
import model.*;
import model.strategies.*;
import java.awt.*;
import javax.swing.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.LayoutManager;
import java.awt.TextArea;
import java.awt.Color;
import javax.swing.JButton;
//import java.awt.SystemColor;
import javax.swing.JPanel;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import java.awt.event.*;
import java.awt.Font;


public class LatexEditorView implements WindowListener,ActionListener {
	private static  LatexEditorView window;
	private static LatexEditorController latexEditorController;
	private static InitialFileChooser initialFileChooser;
	private String templateID,openPath,savePath,loadPath,latexCommand,choice;
	private int mouse_position;
	private boolean enabledBefore=false;
	private VersionsManager versionsManager;
	private DocumentManager documentManager;
	private VersionsStrategyFactory versionsFactory=new VersionsStrategyFactory() ;
	private JFrame mainFrame,infoFrame;//,frame2;
	private JPanel fieldsPanel,buttonPanel;
	private JButton btnOk,btnDisable, btnEnable;
	private JLabel author,copyright;
	private JTextField authorField,copyrightField;
	private JRadioButton rdbtnStable,rdbtnVolatile;
	private TextArea textArea;
	private ButtonGroup buttonGroup;
	private LoadFileChooser loadFileChooser;
	private Font font = new Font("Roman Font",Font.ROMAN_BASELINE,16);
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new LatexEditorView();
					latexEditorController=new LatexEditorController(window);
					window.mainFrame.setVisible(true);
					initialFileChooser = window.new InitialFileChooser();
					initialFileChooser.setSize(700, 100);
					initialFileChooser.setLocation(450, 220);
					JLabel onlyLabel = new JLabel("Please enter the path to the Latex templates and choose a path where your work will be saved.");
					initialFileChooser.add(onlyLabel, BorderLayout.AFTER_LINE_ENDS);
					initialFileChooser.setVisible(true);
	    		    
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
	}

	public LatexEditorView() {
		versionsManager=new VersionsManager(null);
		documentManager= new DocumentManager();
		initialize();
	}
	

	private void initialize() {

		mainFrame = new JFrame("LatexEditor");
		mainFrame.setType(Type.NORMAL);
		mainFrame.setBounds(100, 100, 1500, 1000);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		textArea = new TextArea();
		textArea.setBackground(new Color(205, 2, 103));
		//textArea.setBackground(new Color(194, 243, 255));
		textArea.setForeground(Color.BLACK);
		textArea.setFont(font);
		mainFrame.getContentPane().add(textArea, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255,206,194));
		//menuBar.setBackground(new Color(155, 2, 70));
		mainFrame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setForeground(Color.BLACK);
		mnFile.setBackground(new Color(255,206,194));
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setForeground(Color.BLACK);
		mntmSave.setBackground(new Color(255,206,194));
		mntmSave.addActionListener(this);
		mnFile.add(mntmSave);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.setForeground(Color.BLACK);
		mntmLoad.setBackground(new Color(255,206,194));
		mntmLoad.addActionListener(this);
		mnFile.add(mntmLoad);
		
		JMenu mnNewMenu = new JMenu("LaTex Commands");
		mnNewMenu.setBackground(new Color(255,206,194));
		mnNewMenu.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmChapter = new JMenuItem("\\chapter{..}");
		mntmChapter.setForeground(Color.BLACK);
		mntmChapter.setBackground(new Color(255,206,194));
		mntmChapter.addActionListener(this);
		mnNewMenu.add(mntmChapter);
		
		JMenuItem mntmSection = new JMenuItem("\\section{}");
		mntmSection.setForeground(Color.BLACK);
		mntmSection.setBackground(new Color(255,206,194));
		mntmSection.addActionListener(this);
		mnNewMenu.add(mntmSection);
		
		JMenuItem mntmSubsection = new JMenuItem("\\subsection{}");
		mntmSubsection.setForeground(Color.BLACK);
		mntmSubsection.setBackground(new Color(255,206,194));
		mntmSubsection.addActionListener(this);
		mnNewMenu.add(mntmSubsection);
		
		JMenuItem mntmSubsubsection = new JMenuItem("\\subsubsection{}");
		mntmSubsubsection.setForeground(Color.BLACK);
		mntmSubsubsection.setBackground(new Color(255,206,194));
		mntmSubsubsection.addActionListener(this);
		mnNewMenu.add(mntmSubsubsection);
		
		JMenuItem mnItemize = new JMenuItem("Itemize");
		mnItemize.setBackground(new Color(255,206,194));
		mnItemize.setForeground(Color.BLACK);
		mnItemize.addActionListener(this);
		mnNewMenu.add(mnItemize);

		
		JMenuItem mnTable = new JMenuItem("Table");
		mnTable.setForeground(Color.BLACK);
		mnTable.setBackground(new Color(255,206,194));
		mnTable.addActionListener(this);
		mnNewMenu.add(mnTable);
		
		JMenuItem mnEnumerate = new JMenuItem("Enumerate");
		mnEnumerate.setForeground(Color.BLACK);
		mnEnumerate.setBackground(new Color(255,206,194));
		mnEnumerate.addActionListener(this);
		mnNewMenu.add(mnEnumerate);
		
		JMenuItem mnFigure = new JMenuItem("Figure");
		mnFigure.setForeground(Color.BLACK);
		mnFigure.setBackground(new Color(255,206,194));
		mnFigure.addActionListener(this);
		mnNewMenu.add(mnFigure);

		JMenu mnTemplates = new JMenu("LaTex Templates");
		mnTemplates.setForeground(Color.BLACK);
		mnTemplates.setBackground(new Color(255,206,194));
		menuBar.add(mnTemplates);
		
		JMenuItem mntmBook = new JMenuItem("Book");
		mntmBook.setForeground(Color.BLACK);
		mntmBook.setBackground(new Color(255,206,194));
		mntmBook.addActionListener(this);
		mnTemplates.add(mntmBook);
		
		JMenuItem mntmArticle = new JMenuItem("Article");
		mntmArticle.setForeground(Color.BLACK);
		mntmArticle.setBackground(new Color(255,206,194));
		mntmArticle.addActionListener(this);
		mnTemplates.add(mntmArticle);
		
		JMenuItem mntmLetter = new JMenuItem("Letter");
		mntmLetter.setForeground(Color.BLACK);
		mntmLetter.setBackground(new Color(255,206,194));
		mntmLetter.addActionListener(this);
		mnTemplates.add(mntmLetter);
		
		JMenuItem mntmReport = new JMenuItem("Report");
		mntmReport.setForeground(Color.BLACK);
		mntmReport.setBackground(new Color(255,206,194));
		mntmReport.addActionListener(this);
		mnTemplates.add(mntmReport);
		
		JMenuItem mntmEmpty = new JMenuItem("Empty");
		mntmEmpty.setForeground(Color.BLACK);
		mntmEmpty.setBackground(new Color(255,206,194));
		mntmEmpty.addActionListener(this);
		mnTemplates.add(mntmEmpty);
		
		
		btnEnable = new JButton("Enable");
		btnEnable.setBackground(new Color(255,206,194));
		btnEnable.addActionListener(this);
        menuBar.add(btnEnable);
        
		rdbtnVolatile = new JRadioButton("Volatile");
		rdbtnVolatile.setForeground(Color.BLACK);
		rdbtnVolatile.setBackground(new Color(255,206,194));
		rdbtnVolatile.addActionListener(this);
		menuBar.add(rdbtnVolatile);
		
		rdbtnStable = new JRadioButton("Stable");
		rdbtnStable.setForeground(Color.BLACK);
		rdbtnStable.setBackground(new Color(255,206,194));
		rdbtnStable.addActionListener(this);
		menuBar.add(rdbtnStable);
		
		buttonGroup = new ButtonGroup();
	    buttonGroup.add(rdbtnStable);
	    buttonGroup.add(rdbtnVolatile);
	         
	    btnDisable = new JButton("Disable");
	    btnDisable.setBackground(new Color(255,206,194));
	    btnDisable.addActionListener(this);
	    menuBar.add(btnDisable);
		
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.setForeground(Color.BLACK);
		btnUndo.setBackground(new Color(255,206,194));
		btnUndo.addActionListener(this);
		menuBar.add(btnUndo);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(Color.BLACK);
		btnSubmit.setBackground(new Color(255,206,194));
		btnSubmit.addActionListener(this);
		menuBar.add(btnSubmit);
	}
	
    private void infoWindow(){

		infoFrame= new JFrame("Let's Start!");
		infoFrame.setBounds(450, 220, 300, 100);
		
		buttonPanel=new JPanel();
		fieldsPanel= new JPanel();
		
		author=new JLabel("Please enter the author's name.");
		authorField=new JTextField("");
		copyright=new JLabel("Please enter the copyrights, if any.");		
		copyrightField=new JTextField("");
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		
		fieldsPanel.setLayout((LayoutManager) new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setLayout(new FlowLayout());

        fieldsPanel.add(author);
        fieldsPanel.add(authorField);
        fieldsPanel.add(copyright);
        fieldsPanel.add(copyrightField);
               
        infoFrame.add(fieldsPanel, BorderLayout.PAGE_START);
        infoFrame.add(buttonPanel, BorderLayout.PAGE_END);
        infoFrame.setVisible(true);	
        
        buttonPanel.add(btnOk); 
    }
    
    
    
    
    
    public class InitialFileChooser extends JFrame {
    	  private JTextField filename = new JTextField(), directory = new JTextField();
    	  private JButton open = new JButton("Open"), save = new JButton("Save");

    	  public InitialFileChooser() {
    		
    	    JPanel panel = new JPanel();
    	    
    	    open.addActionListener(new OpenListener());
    	    panel.add(open);
    	    
    	    save.addActionListener(new SaveListener());
    	    panel.add(save);
    	    
    	    Container contentPane = getContentPane();
    	    contentPane.add(panel, BorderLayout.SOUTH);
    	    
    	    directory.setEditable(false);
    	    filename.setEditable(false);
    	    
    	    panel = new JPanel();
    	    panel.setLayout(new GridLayout(2, 1));
    	    panel.add(filename);
    	    panel.add(directory);
    	    contentPane.add(panel, BorderLayout.NORTH);
    	  }
    	  

    	  class OpenListener implements ActionListener {
    		  public void actionPerformed(ActionEvent e) {
    			  
	    	      JFileChooser chooser = new JFileChooser();
	    	      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	      int rVal = chooser.showOpenDialog(InitialFileChooser.this);
	    	      
	    	      if (rVal == JFileChooser.APPROVE_OPTION) {
	    	        filename.setText(chooser.getSelectedFile().getName());
	    	        directory.setText(chooser.getCurrentDirectory().toString());
	    	        openPath=directory.getText()+'/'+filename.getText();
	    	        window.documentManager.dynamicallyLoad(openPath);
    	      }
    	      if (rVal == JFileChooser.CANCEL_OPTION) {
    	        filename.setText("You pressed cancel");
    	        directory.setText("");
    	      }
    	    }
    	  }

    	  class SaveListener implements ActionListener {
    	    public void actionPerformed(ActionEvent e) {
    	    	
	    	      JFileChooser chooser = new JFileChooser();
	    	      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	      int rVal = chooser.showSaveDialog(InitialFileChooser.this);
	    	      
	    	      if (rVal == JFileChooser.APPROVE_OPTION) {
	    	        filename.setText(chooser.getSelectedFile().getName());
	    	        directory.setText(chooser.getCurrentDirectory().toString());
	    	        savePath=directory.getText()+'/'+filename.getText();
	    	        initialFileChooser.setVisible(false);
	    	      }
	    	      if (rVal == JFileChooser.CANCEL_OPTION) {
	    	        filename.setText("You pressed cancel");
	    	        directory.setText("");
	    	      }
	    	}
    	  }
    }

    public class LoadFileChooser extends JFrame {
  	  private JTextField filename = new JTextField(), directory = new JTextField();
  	  private JButton open = new JButton("Load");

  	  public LoadFileChooser() {
  		  
  	    JPanel panel = new JPanel();
  	    open.addActionListener(new OpenListener());
  	    panel.add(open);
  	    
  	    Container contentPane = getContentPane();
  	    contentPane.add(panel, BorderLayout.SOUTH);
  	    
  	    directory.setEditable(false);
  	    filename.setEditable(false);
  	    
  	    panel = new JPanel();
  	    panel.setLayout(new GridLayout(2, 1));
  	    panel.add(filename);
  	    panel.add(directory);
  	    contentPane.add(panel, BorderLayout.NORTH);
  	  }
  	  

  	  class OpenListener implements ActionListener {
	  	    public void actionPerformed(ActionEvent e) {
	  	    	
	  	      JFileChooser chooser = new JFileChooser();
	  	      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	  	      int rVal = chooser.showOpenDialog(LoadFileChooser.this);
	  	      
	  	      if (rVal == JFileChooser.APPROVE_OPTION) {
	  	        filename.setText(chooser.getSelectedFile().getName());
	  	        directory.setText(chooser.getCurrentDirectory().toString());
	  	        loadPath=directory.getText()+'/'+filename.getText();
	  	        setVisible(false);
	  	       latexEditorController.enact("Load");
	  	       
	  	      }
	  	      if (rVal == JFileChooser.CANCEL_OPTION) {
	  	        filename.setText("You pressed cancel");
	  	        directory.setText("");
	  	      }
	  	    }
  	  } 
    }

	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Book")){
			
			templateID="Book";
			latexEditorController.enact("Create");
			textArea.setText(versionsManager.getCurrentDocument().getContents());
			infoWindow();
		}else if(e.getActionCommand().equals("Article")){
			
			templateID="Article";
			latexEditorController.enact("Create");
			textArea.setText(versionsManager.getCurrentDocument().getContents());
			infoWindow();
		}else if(e.getActionCommand().equals("Report")){
			
			templateID="Report";
			latexEditorController.enact("Create");
			textArea.setText(versionsManager.getCurrentDocument().getContents());
			infoWindow();
		}else if(e.getActionCommand().equals("Empty")){
			
			templateID="Empty";
			latexEditorController.enact("Create");
			textArea.setText(versionsManager.getCurrentDocument().getContents());
			infoWindow();
		}else if(e.getActionCommand().equals("Letter")){
			
			templateID="Letter";
			latexEditorController.enact("Create");
			textArea.setText(versionsManager.getCurrentDocument().getContents());
			infoWindow();
		}else if(e.getActionCommand().equals("OK")){
			
			if(!authorField.getText().equals("")) {
				versionsManager.getCurrentDocument().setAuthor(authorField.getText());
				versionsManager.getCurrentDocument().setCopyright(copyrightField.getText());
				infoFrame.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Error: The author field is mandatory","Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if((e.getActionCommand().equals("Submit"))){
			
			latexEditorController.enact("Edit");
		}else if(e.getActionCommand().equals("\\chapter{..}")){
			
			mouse_position=getTextArea().getCaretPosition();
			if (templateID.equals("Letter") ||templateID.equals("Article")){
				JOptionPane.showMessageDialog(null, "Error: This Latex command is not allowed in this template.","Error", JOptionPane.ERROR_MESSAGE);
			}else{
				latexCommand = "\\chapter{..}";
				latexEditorController.enact("AddLatex");
			}
		}else if(e.getActionCommand().equals("\\section{}")){
			
			mouse_position=getTextArea().getCaretPosition();
			if (templateID.equals("Letter")){
				JOptionPane.showMessageDialog(null, "Error: This Latex command is not allowed in this template.","Error", JOptionPane.ERROR_MESSAGE);
			}else{
				latexCommand = "\\section{}";
				latexEditorController.enact("AddLatex");
			}
		
		}else if(e.getActionCommand().equals("\\subsection{}")){
			
			mouse_position=getTextArea().getCaretPosition();
			if (templateID.equals("Letter")){
				JOptionPane.showMessageDialog(null, "Error: This Latex command is not allowed in this template.","Error", JOptionPane.ERROR_MESSAGE);
			}else{
				latexCommand = "\\subsection{}";
				latexEditorController.enact("AddLatex");
			}
		
		}else if(e.getActionCommand().equals("\\subsubsection{}")){
			
			mouse_position=getTextArea().getCaretPosition();
			if (templateID.equals("Letter")){
				JOptionPane.showMessageDialog(null, "Error: This Latex command is not allowed in this template.","Error", JOptionPane.ERROR_MESSAGE);
			}else{
				latexCommand = "\\subsubsection{}";
				latexEditorController.enact("AddLatex");
			}
		
		}else if(e.getActionCommand().equals("Itemize")){
			
			mouse_position=getTextArea().getCaretPosition();
			if (templateID.equals("Letter")){
				JOptionPane.showMessageDialog(null, "Error: This Latex command is not allowed in this template.","Error", JOptionPane.ERROR_MESSAGE);
			}else{
				latexCommand = "\\begin{itemize} \n\\item ...\n\\item ...\n\\end{itemize}";
				latexEditorController.enact("AddLatex");
			}
		
		}else if(e.getActionCommand().equals("Enumerate")){
			
			mouse_position=getTextArea().getCaretPosition();
			if (templateID.equals("Letter")){
				JOptionPane.showMessageDialog(null, "Error: This Latex command is not allowed in this template.","Error", JOptionPane.ERROR_MESSAGE);
			}else{
				latexCommand = "\\begin{enumerate}\n\\item ...\n\\item ...\n\\end{enumerate}";
				latexEditorController.enact("AddLatex");
			}
		
		}else if(e.getActionCommand().equals("Table")){
			
			mouse_position=getTextArea().getCaretPosition();
			if (templateID.equals("Letter")){
				JOptionPane.showMessageDialog(null, "Error: This Latex command is not allowed in this template.","Error", JOptionPane.ERROR_MESSAGE);
			}else{
				latexCommand = "\\begin{table}\n\\caption{....}\\label{...} \n \\begin{tabular}{|c|c|c|} \n \\hline \n... &...&...\\ \n ... &...&...\\ \n ... &...&...\\ \n\\hline \n\\end{tabular} \n\\end{table}";
				latexEditorController.enact("AddLatex");
			}
		
		}else if(e.getActionCommand().equals("Figure")){
			
			mouse_position=getTextArea().getCaretPosition();
			if (templateID.equals("Letter")){
				JOptionPane.showMessageDialog(null, "Error: This Latex command is not allowed in this template.","Error", JOptionPane.ERROR_MESSAGE);
			}else{
				latexCommand = "\\begin{figure}\\includegraphics[width=...,height=...]{...} \n \\caption{....}\\label{...} \n \\end{figure}";
				latexEditorController.enact("AddLatex");
			}
		/*}else if(e.getActionCommand().equals("OKAY")){
			frame2.dispose();*/
		}else if(e.getActionCommand().equals("Enable")){
			
			choice="Volatile";
			enabledBefore=true;
			latexEditorController.enact("EnableVersionsManagement");
			
			
			
		}else if(e.getActionCommand().equals("Disable")){
			
			latexEditorController.enact("DisableVersionsManagement");
		}else if(e.getActionCommand().equals("Volatile")){
			
			if(!choice.equals( "Volatile")){	
				latexEditorController.enact("ChangeVersionsStrategy");
			}
	
		}else if(e.getActionCommand().equals("Stable")){
			
			if(!choice.equals( "Stable")){
				latexEditorController.enact("ChangeVersionsStrategy");
			}
		}else if(e.getActionCommand().equals("Save")){
			
			latexEditorController.enact("Save");
		}else if(e.getActionCommand().equals("Undo")){
			
			if(enabledBefore==false){
				JOptionPane.showMessageDialog(null, "Error: Tracking has not been enabled yet!","Error", JOptionPane.ERROR_MESSAGE);
			}else{
				latexEditorController.enact("RollbackToPreviousVersion");
			}
			
		}else if(e.getActionCommand().equals("Load")){
			
			if (!versionsManager.isEnabled()){
				JOptionPane.showMessageDialog(null, "Error: Select strategy.","Error", JOptionPane.ERROR_MESSAGE);
			}
			loadFileChooser = window.new LoadFileChooser();
		    loadFileChooser.setSize(250, 150);
		    loadFileChooser.setVisible(true);
			
		}	
	}

	
	public String getTemplateID() {
		return templateID;
	}

	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}

	public VersionsManager getVersionsManager() {
		return versionsManager;
	}

	public void setVersionsManager(VersionsManager versionsManager) {
		this.versionsManager = versionsManager;
	}

	public DocumentManager getDocumentManager() {
		return documentManager;
	}

	public void setDocumentManager(DocumentManager documentManager) {
		this.documentManager = documentManager;
	}

	public TextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(String text) {
		this.textArea.setText(text);
	}

	public String getLatexCommand() {
		return latexCommand;
	}

	public void setLatexCommand(String latexCommand) {
		this.latexCommand = latexCommand;
	}

	public int getMouse_position() {
		return mouse_position;
	}

	public void setMouse_position(int mouse_position) {
		this.mouse_position = mouse_position;
	}

	public JButton getBtnDisable() {
		return btnDisable;
	}

	public void setBtnDisable(JButton btnDisable) {
		this.btnDisable = btnDisable;
	}

	public JButton getBtnEnable() {
		return btnEnable;
	}

	public void setBtnEnable(JButton btnEnable) {
		this.btnEnable = btnEnable;
	}

	public JRadioButton getRdbtnStable() {
		return rdbtnStable;
	}

	public void setRdbtnStable(JRadioButton rdbtnStable) {
		this.rdbtnStable = rdbtnStable;
	}

	public JRadioButton getRdbtnVolatile() {
		return rdbtnVolatile;
	}

	public void setRdbtnVolatile(JRadioButton rdbtnVolatile) {
		this.rdbtnVolatile = rdbtnVolatile;
	}

	public VersionsStrategyFactory getVersionsFactory() {
		return versionsFactory;
	}

	public void setVersionsFactory(VersionsStrategyFactory versionsFactory) {
		this.versionsFactory = versionsFactory;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getLoadPath() {
		return loadPath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setLoadPath(String loadPath) {
		this.loadPath = loadPath;
	}

	public void setOpenPath(String openPath) {
		this.openPath = openPath;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	



}

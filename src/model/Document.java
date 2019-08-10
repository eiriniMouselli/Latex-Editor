package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public  class Document implements java.io.Serializable{
    

    private String author;
    private LocalDate date;
    private int versionID=0;
    private String copyright;
    private String contents;
    private String templateID;
    
    public Document(String templateID,String contents){
    	this.templateID = templateID;
    	this.contents = contents;
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    	LocalDate localDate = LocalDate.now();
    	this.date = localDate;
    }

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setVersionID(int versionID) {
		this.versionID = versionID;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}

	public void save(){
		
	}

	public Document clone() {	 

		return new Document(new String(this.templateID) , new String(this.contents));
	}

	public String getAuthor() {
		return author;
	}

	public LocalDate getDate() {
		return date;
	}
	

	public int getVersionID() {
		return versionID;
	}

	public String getCopyright() {
		return copyright;
	}

	public String getContents() {
		return contents;
	}

	public String getTemplateID() {
		return templateID;
	}
}




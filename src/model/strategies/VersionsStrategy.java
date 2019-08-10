package model.strategies;

import java.util.ArrayList;

import model.*;
public interface VersionsStrategy {

	public void putVersion(Document document);
	public Document getVersion();
	public void setEntireHistory(ArrayList<Document> historyList);
	public ArrayList<Document> getEntireHistory();
	public void removeVersion();
	public void setSavePath(String savePath);
	public String getSavePath();
	public int getIndex();
	public void setIndex(int index);
	public void setCounter(int counter);
	public int getCounter();
}

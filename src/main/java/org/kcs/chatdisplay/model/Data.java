package org.kcs.chatdisplay.model;

import java.util.List;

public class Data {

	private String databaseName;
	private int databaseVersion;
	private Table[] tables;
	private List<CharacterData> data;
	
	public List<CharacterData> getData() {
		return data;
	}
	public void setData(List<CharacterData> data) {
		this.data = data;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	public int getDatabaseVersion() {
		return databaseVersion;
	}
	public void setDatabaseVersion(int databaseVersion) {
		this.databaseVersion = databaseVersion;
	}
	public Table[] getTables() {
		return tables;
	}
	public void setTables(Table[] tables) {
		this.tables = tables;
	}
}

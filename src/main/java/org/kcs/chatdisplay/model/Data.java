package org.kcs.chatdisplay.model;

import java.util.List;

public class Data {

	private String databaseName;
	private int databaseVersion;
	private List<Table> tables;
	private CharacterData data;
	
	public CharacterData getData() {
		return data;
	}
	public void setData(CharacterData data) {
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
	public List<Table> getTables() {
		return tables;
	}
	public void setTables(List<Table> tables) {
		this.tables = tables;
	}
}

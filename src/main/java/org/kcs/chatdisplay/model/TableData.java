package org.kcs.chatdisplay.model;

public class TableData {

	private String tableName;
	private boolean inbound;
	private Character[] characters;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public boolean isInbound() {
		return inbound;
	}
	public void setInbound(boolean inbound) {
		this.inbound = inbound;
	}
	public Character[] getCharacters() {
		return characters;
	}
	public void setCharacters(Character[] characters) {
		this.characters = characters;
	}
	
	
}

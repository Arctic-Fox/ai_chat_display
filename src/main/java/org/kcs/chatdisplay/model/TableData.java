package org.kcs.chatdisplay.model;

import java.util.List;

public class TableData {

	private String tableName;
	private boolean inbound;
	private List<Character> characters;
	
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
	public List<Character> getCharacters() {
		return characters;
	}
	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}
	
	
}

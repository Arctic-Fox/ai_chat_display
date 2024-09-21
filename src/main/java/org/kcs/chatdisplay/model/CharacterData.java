package org.kcs.chatdisplay.model;

import java.util.List;

public class CharacterData {

	private String tableName;
	private boolean inbound;
	private List<Character> rows;
	
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
	public List<Character> getRows() {
		return rows;
	}
	public void setRows(List<Character> rows) {
		this.rows = rows;
	}

	
	
}

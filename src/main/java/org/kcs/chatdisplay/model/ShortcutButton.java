package org.kcs.chatdisplay.model;

public class ShortcutButton {

	private String name;
	private String message;
	private boolean autoSend;
	private String type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isAutoSend() {
		return autoSend;
	}
	public void setAutoSend(boolean autoSend) {
		this.autoSend = autoSend;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

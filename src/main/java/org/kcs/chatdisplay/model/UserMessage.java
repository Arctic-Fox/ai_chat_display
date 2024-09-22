package org.kcs.chatdisplay.model;

public class UserMessage {

	private String text;
	private boolean isPinned;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isPinned() {
		return isPinned;
	}
	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}
}

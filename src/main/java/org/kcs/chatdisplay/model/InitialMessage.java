package org.kcs.chatdisplay.model;

public class InitialMessage {

	private String author;
	private String content;
	private String[] hiddenFrom;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getHiddenFrom() {
		return hiddenFrom;
	}
	public void setHiddenFrom(String[] hiddenFrom) {
		this.hiddenFrom = hiddenFrom;
	}
	
	
}

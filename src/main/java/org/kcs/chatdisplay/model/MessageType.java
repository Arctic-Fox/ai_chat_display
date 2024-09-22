package org.kcs.chatdisplay.model;

public class MessageType {

	private String userMessagesSentHistory;
	private String shortcutButtons;
	private String currentSummaryHashChain;
	
	public String getUserMessagesSentHistory() {
		return userMessagesSentHistory;
	}
	public void setUserMessagesSentHistory(String userMessagesSentHistory) {
		this.userMessagesSentHistory = userMessagesSentHistory;
	}
	public String getShortcutButtons() {
		return shortcutButtons;
	}
	public void setShortcutButtons(String shortcutButtons) {
		this.shortcutButtons = shortcutButtons;
	}
	public String getCurrentSummaryHashChain() {
		return currentSummaryHashChain;
	}
	public void setCurrentSummaryHashChain(String currentSummaryHashChain) {
		this.currentSummaryHashChain = currentSummaryHashChain;
	}
	
	
}

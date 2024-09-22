package org.kcs.chatdisplay.model;

import java.util.Date;
import java.util.List;

public class Thread {

	private String name;
	private int characterId;
	private Date creationTime;
	private Date lastMessageTime;
	private Date lastViewTime;
	private boolean isFav;
	private UserCharacter userCharacter;
	private Character character;
	private String modelName;
	private CustomCodeWindow customCodeWindow;
	private CustomData customData;
	private String folderPath;
	private int loreBookId;
	private String textEmbeddingModelName;
	private List<UserMessage> userMessageSentHistory;
	private String unsentMessageText;
	private List<ShortcutButton> shortcutButtons; 
	private String[] currentSummaryHashChain;
	private int id;
	private String messageWrapperStyle;
	private MessageType $types;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCharacterId() {
		return characterId;
	}
	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getLastMessageTime() {
		return lastMessageTime;
	}
	public void setLastMessageTime(Date lastMessageTime) {
		this.lastMessageTime = lastMessageTime;
	}
	public Date getLastViewTime() {
		return lastViewTime;
	}
	public void setLastViewTime(Date lastViewTime) {
		this.lastViewTime = lastViewTime;
	}
	public boolean isFav() {
		return isFav;
	}
	public void setFav(boolean isFav) {
		this.isFav = isFav;
	}
	public UserCharacter getUserCharacter() {
		return userCharacter;
	}
	public void setUserCharacter(UserCharacter userCharacter) {
		this.userCharacter = userCharacter;
	}
	public Character getCharacter() {
		return character;
	}
	public void setCharacter(Character character) {
		this.character = character;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public CustomCodeWindow getCustomCodeWindow() {
		return customCodeWindow;
	}
	public void setCustomCodeWindow(CustomCodeWindow customCodeWindow) {
		this.customCodeWindow = customCodeWindow;
	}
	public CustomData getCustomData() {
		return customData;
	}
	public void setCustomData(CustomData customData) {
		this.customData = customData;
	}
	public String getFolderPath() {
		return folderPath;
	}
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
	public int getLoreBookId() {
		return loreBookId;
	}
	public void setLoreBookId(int loreBookId) {
		this.loreBookId = loreBookId;
	}
	public String getTextEmbeddingModelName() {
		return textEmbeddingModelName;
	}
	public void setTextEmbeddingModelName(String textEmbeddingModelName) {
		this.textEmbeddingModelName = textEmbeddingModelName;
	}
	public List<UserMessage> getUserMessageSentHistory() {
		return userMessageSentHistory;
	}
	public void setUserMessageSentHistory(List<UserMessage> userMessageSentHistory) {
		this.userMessageSentHistory = userMessageSentHistory;
	}
	public String getUnsentMessageText() {
		return unsentMessageText;
	}
	public void setUnsentMessageText(String unsentMessageText) {
		this.unsentMessageText = unsentMessageText;
	}
	public List<ShortcutButton> getShortcutButtons() {
		return shortcutButtons;
	}
	public void setShortcutButtons(List<ShortcutButton> shortcutButtons) {
		this.shortcutButtons = shortcutButtons;
	}
	public String[] getCurrentSummaryHashChain() {
		return currentSummaryHashChain;
	}
	public void setCurrentSummaryHashChain(String[] currentSummaryHashChain) {
		this.currentSummaryHashChain = currentSummaryHashChain;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessageWrapperStyle() {
		return messageWrapperStyle;
	}
	public void setMessageWrapperStyle(String messageWrapperStyle) {
		this.messageWrapperStyle = messageWrapperStyle;
	}
	public MessageType get$types() {
		return $types;
	}
	public void set$types(MessageType $types) {
		this.$types = $types;
	}
	
	
	
	
}

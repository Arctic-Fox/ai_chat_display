package org.kcs.chatdisplay.model;

import java.util.Date;
import java.util.List;

public class Character {

	private String name;
	private String roleInstruction;
	private int maxParagraphCountPerMessage;
	private String reminderMessage;
	private String imagePromptPrefix;
	private String imagePromptSuffix;
	private String imagePromptTriggers;
	private String fitMessagesInContextMethod;
	private String autoGenerateMemories;
	private String messageWrapperStyle;
	private String customCode;
	private String messageInputPlaceholder;
	private String metaTitle;
	private String metaDescription;
	private String metaImage;
	private String modelName;
	private String textEmbeddingModelNamel;
	private float temperature;
	private int maxTokensPerMessage;
	private List<InitialMessage> initialMessages;
	private String[] loreBookUrls;
	private Avatar avatar;
	private Scene scene;
	private UserCharacter userCharacter;
	private SystemCharacter systemCharacter;
	private boolean streamingResponse;
	
	public List<InitialMessage> getInitialMessages() {
		return initialMessages;
	}
	public void setInitialMessages(List<InitialMessage> initialMessages) {
		this.initialMessages = initialMessages;
	}
	public String[] getLoreBookUrls() {
		return loreBookUrls;
	}
	public void setLoreBookUrls(String[] loreBookUrls) {
		this.loreBookUrls = loreBookUrls;
	}
	public Avatar getAvatar() {
		return avatar;
	}
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	public UserCharacter getUserCharacter() {
		return userCharacter;
	}
	public void setUserCharacter(UserCharacter userCharacter) {
		this.userCharacter = userCharacter;
	}
	public SystemCharacter getSystemCharacter() {
		return systemCharacter;
	}
	public void setSystemCharacter(SystemCharacter systemCharacter) {
		this.systemCharacter = systemCharacter;
	}
	public boolean isStreamingResponse() {
		return streamingResponse;
	}
	public void setStreamingResponse(boolean streamingResponse) {
		this.streamingResponse = streamingResponse;
	}
	public String getFolderPath() {
		return folderPath;
	}
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
	public String[] getCustomData() {
		return customData;
	}
	public void setCustomData(String[] customData) {
		this.customData = customData;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String[] getShortcutButtons() {
		return shortcutButtons;
	}
	public void setShortcutButtons(String[] shortcutButtons) {
		this.shortcutButtons = shortcutButtons;
	}
	public Types get$types() {
		return $types;
	}
	public void set$types(Types $types) {
		this.$types = $types;
	}
	private String folderPath;
	private String[] customData;
	private String uuid;
	private Date creationTime;
	private Date lastMessageTime;
	private int id;
	private String[] shortcutButtons;
	private Types $types;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoleInstruction() {
		return roleInstruction;
	}
	public void setRoleInstruction(String roleInstruction) {
		this.roleInstruction = roleInstruction;
	}
	public int getMaxParagraphCountPerMessage() {
		return maxParagraphCountPerMessage;
	}
	public void setMaxParagraphCountPerMessage(int maxParagraphCountPerMessage) {
		this.maxParagraphCountPerMessage = maxParagraphCountPerMessage;
	}
	public String getReminderMessage() {
		return reminderMessage;
	}
	public void setReminderMessage(String reminderMessage) {
		this.reminderMessage = reminderMessage;
	}
	public String getImagePromptPrefix() {
		return imagePromptPrefix;
	}
	public void setImagePromptPrefix(String imagePromptPrefix) {
		this.imagePromptPrefix = imagePromptPrefix;
	}
	public String getImagePromptSuffix() {
		return imagePromptSuffix;
	}
	public void setImagePromptSuffix(String imagePromptSuffix) {
		this.imagePromptSuffix = imagePromptSuffix;
	}
	public String getImagePromptTriggers() {
		return imagePromptTriggers;
	}
	public void setImagePromptTriggers(String imagePromptTriggers) {
		this.imagePromptTriggers = imagePromptTriggers;
	}
	public String getFitMessagesInContextMethod() {
		return fitMessagesInContextMethod;
	}
	public void setFitMessagesInContextMethod(String fitMessagesInContextMethod) {
		this.fitMessagesInContextMethod = fitMessagesInContextMethod;
	}
	public String getAutoGenerateMemories() {
		return autoGenerateMemories;
	}
	public void setAutoGenerateMemories(String autoGenerateMemories) {
		this.autoGenerateMemories = autoGenerateMemories;
	}
	public String getMessageWrapperStyle() {
		return messageWrapperStyle;
	}
	public void setMessageWrapperStyle(String messageWrapperStyle) {
		this.messageWrapperStyle = messageWrapperStyle;
	}
	public String getCustomCode() {
		return customCode;
	}
	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}
	public String getMessageInputPlaceholder() {
		return messageInputPlaceholder;
	}
	public void setMessageInputPlaceholder(String messageInputPlaceholder) {
		this.messageInputPlaceholder = messageInputPlaceholder;
	}
	public String getMetaTitle() {
		return metaTitle;
	}
	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}
	public String getMetaDescription() {
		return metaDescription;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	public String getMetaImage() {
		return metaImage;
	}
	public void setMetaImage(String metaImage) {
		this.metaImage = metaImage;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getTextEmbeddingModelNamel() {
		return textEmbeddingModelNamel;
	}
	public void setTextEmbeddingModelNamel(String textEmbeddingModelNamel) {
		this.textEmbeddingModelNamel = textEmbeddingModelNamel;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public int getMaxTokensPerMessage() {
		return maxTokensPerMessage;
	}
	public void setMaxTokensPerMessage(int maxTokensPerMessage) {
		this.maxTokensPerMessage = maxTokensPerMessage;
	}
	
	
}

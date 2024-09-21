package org.kcs.chatdisplay.model;

public class Archive {

	private String formatName;
	private int formatVersion;
	private Data data;
	
	public String getFormatName() {
		return formatName;
	}
	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}
	public int getFormatVersion() {
		return formatVersion;
	}
	public void setFormatVersion(int formatVersion) {
		this.formatVersion = formatVersion;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
}

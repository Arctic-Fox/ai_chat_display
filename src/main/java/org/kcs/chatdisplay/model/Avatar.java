package org.kcs.chatdisplay.model;

import org.kcs.chatdisplay.util.AvatarShape;

public class Avatar {

	private String url;
	private int size;
	private AvatarShape shape;
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public AvatarShape getShape() {
		return shape;
	}

	public void setShape(AvatarShape shape) {
		this.shape = shape;
	}
}

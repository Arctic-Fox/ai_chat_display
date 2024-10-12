package org.kcs.chatdisplay.util;

import java.awt.image.BufferedImage;
import java.util.HashSet;

public class Utilities {
	
	private HashSet<BufferedImage> imageIndex = new HashSet<>();
	private static Utilities utilities;
	
	public static Utilities getInstance() {
		if(utilities == null) {
			utilities = new Utilities();
		}
		return utilities;
	}
	
	private Utilities() {}
	
	public boolean addImage(BufferedImage image) {
		return imageIndex.add(image);
	}
	
	public HashSet<BufferedImage> getImages() {
		return imageIndex;
	}

}

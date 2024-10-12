package org.kcs.chatdisplay.util;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	static class ImageHash {
		final int hashCode;
		final BufferedImage image;

		ImageHash(BufferedImage image) {
			hashCode = image.getData().hashCode();
			this.image = null;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof ImageHash)) {
				return false;
			}
			ImageHash other = (ImageHash) obj;
			return hashCode == other.hashCode && image.getData().equals(other.image.getData());
		}
	}

	public static boolean isInSet(BufferedImage checkImage, Set<ImageHash> imageHashes) {
		for (ImageHash hash : imageHashes) {
			if (hash.equals(new ImageHash(checkImage))) {
				return true;
			}
		}
		return false;
	}



	public static boolean isInList(BufferedImage checkImage, List<BufferedImage> images) {
		int hash1 = checkImage.getData().hashCode();
		for (BufferedImage image : images) {
			int hash2 = image.getData().hashCode();
			if (hash1 == hash2) {
				// images are identical
				return true;
			}
		}
		return false;
	}
}

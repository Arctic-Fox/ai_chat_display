package org.kcs.chatdisplay;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractImageViewer {

	private static final Logger LOG = LogManager.getLogger(AbstractImageViewer.class);

	protected List<BufferedImage> images = new ArrayList<>();
	protected Set<String> imageSet = new HashSet<>();

	public abstract List<BufferedImage> loadImagesFromJson(String fileText);

	protected boolean isUniqueImage(byte[] imageBytes) {
		String imgString = Arrays.toString(imageBytes);
		if (!imageSet.contains(imgString)) {
			LOG.info("Image is unique.");
			imageSet.add(imgString);
			return true;
		}
		return false;
	}

	protected void processImage(String image) {
		if (image.isBlank()) {
			LOG.error("Image is blank.");
		}
		String base64Data = image;
		base64Data = base64Data.substring(23);
		try {
			byte[] imageBytes = Base64.getDecoder().decode(base64Data);
			if (isUniqueImage(imageBytes)) {
				images.add(ImageIO.read(new ByteArrayInputStream(imageBytes)));
			}
		} catch (Exception e) {
			LOG.error("Failed to decode image: {}", e.getMessage());
		}
	}
}

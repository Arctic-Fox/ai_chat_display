package org.kcs.chatdisplay;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractImageViewer {
	
	private static final Logger LOG = LogManager.getLogger(AbstractImageViewer.class);

	protected static final int PADDING = 100; // Padding for UI components
	protected List<BufferedImage> images = new ArrayList<>();
	protected Set<String> imageSet = new HashSet<>();
	protected int currentImageIndex = -1;
	protected JLabel imageLabel;
	protected JLabel infoLabel;
	protected JFrame frame;
	
	public abstract void loadImagesFromJson(String fileText);

	protected void updateInfoLabel() {
		infoLabel.setText(
				String.format("Image %d of %d (%d unique)", currentImageIndex + 1, images.size(), imageSet.size()));
	}

	protected void updateFrameSize() {
		if (currentImageIndex >= 0) {
			int width = images.get(currentImageIndex).getWidth();
			int height = images.get(currentImageIndex).getHeight();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setSize(Math.min(width, screenSize.width), Math.min(height + PADDING, screenSize.height));
			frame.revalidate();
		}
	}
	
	protected boolean isUniqueImage(byte[] imageBytes) {
		String imgString = Arrays.toString(imageBytes);
		if (!imageSet.contains(imgString)) {
			LOG.info("Image is unique.");
			imageSet.add(imgString);
			return true;
		}
		return false;
	}

	protected void showPreviousImage() {
		if (currentImageIndex > 0) {
			currentImageIndex--;
		} else {
			currentImageIndex = (images.size() - 1);
		}
		updateDisplay();
	}

	protected void showNextImage() {
		if (currentImageIndex < images.size() - 1) {
			currentImageIndex++;
		} else {
			currentImageIndex = 0;
		}
		updateDisplay();
	}

	protected void updateDisplay() {
		if (currentImageIndex >= 0) {
			if (images.get(currentImageIndex) == null) {
				LOG.warn("Current image is null.");
				return;
			}
			imageLabel.setIcon(new ImageIcon(images.get(currentImageIndex)));
			updateInfoLabel();
			updateFrameSize();
		} else {
			imageLabel.setIcon(null);
			infoLabel.setText("No images loaded");
		}
	}
}

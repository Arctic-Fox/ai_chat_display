package org.kcs.chatdisplay.ui;
/**
*Copyright 2024 Chris Jurado
*
*Licensed under the Apache License, Version 2.0 (the "License");
*you may not use this file except in compliance with the License.
*You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
*Unless required by applicable law or agreed to in writing, software
*distributed under the License is distributed on an "AS IS" BASIS,
*WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*See the License for the specific language governing permissions and
*limitations under the License.
*
* Author: Chris Jurado
*/
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kcs.chatdisplay.AbstractImageViewer;
import org.kcs.chatdisplay.GsonImageViewer;

public class MainUi {
	
	private static final Logger LOG = LogManager.getLogger(MainUi.class);
	
	protected JLabel imageLabel;
	protected JLabel infoLabel;
	protected JFrame frame;
	
	protected static final int PADDING = 100; // Padding for UI components

	protected int currentImageIndex = -1;
	private List<BufferedImage> images;
	
	/**
	 * Constructor initializes the UI for image viewing.
	 */
	public MainUi(String fileText) {
		initializeUI();
//      AbstractImageViewer viewer = new JsonImageViewer();
        AbstractImageViewer viewer = new GsonImageViewer();
        images = viewer.loadImagesFromJson(fileText);
		if (images != null && !images.isEmpty()) {
			currentImageIndex = 0;
		}
		LOG.info("Images loaded.  Current image index is {}", currentImageIndex);
		updateDisplay();
	}
	
	private void initializeUI() {
		LOG.info("Initializing UI.");
		frame = new JFrame("MIME Image Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		imageLabel = new JLabel();
		infoLabel = new JLabel("No images loaded");
		JButton prevButton = new JButton("Previous");
		JButton nextButton = new JButton("Next");

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(prevButton);
		buttonPanel.add(nextButton);

		frame.add(imageLabel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(infoLabel, BorderLayout.NORTH);

		prevButton.addActionListener(e -> showPreviousImage());
		nextButton.addActionListener(e -> showNextImage());
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				updateFrameSize();
			}
		});

		frame.pack();
		frame.setVisible(true);
	}
	
	protected void updateInfoLabel() {
//		infoLabel.setText(
//				String.format("Image %d of %d (%d unique)", currentImageIndex + 1, images.size(), imageSet.size()));
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

}

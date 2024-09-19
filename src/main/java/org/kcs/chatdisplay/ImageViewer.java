package org.kcs.chatdisplay;

import javax.json.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ImageViewer class for displaying images from JSON-encoded MIME data. This
 * viewer handles image navigation and display within a Swing-based UI.
 */
public class ImageViewer {
	private static final Logger LOG = LogManager.getLogger(ImageViewer.class);

	private static final int PADDING = 100; // Padding for UI components
	private static final String DEFAULT_IMAGE_FILE = "E:\\cj_personal_archive\\software_projects\\github_repos\\ai_chat_display\\sample_data\\sample2.json";
//    private static final String DEFAULT_IMAGE_FILE = "E:\\cj_personal_archive\\software_projects\\github_repos\\ai_chat_display\\src\\main\\resources\\sample.json";
	private List<BufferedImage> images = new ArrayList<>();
	private Set<String> imageSet = new HashSet<>();
	private int currentImageIndex = -1;
	private JLabel imageLabel;
	private JLabel infoLabel;
	private JFrame frame;

	/**
	 * Constructor initializes the UI for image viewing.
	 */
	public ImageViewer() {
		initializeUI();
	}

	private void initializeUI() {
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

	/**
	 * Loads images from a JSON file path provided.
	 * 
	 * @param filePath The path to the JSON file containing image data.
	 */
	public void loadImagesFromJson(String filePath) {
		LOG.info("Loading from small file.");
		try (InputStream fis = new FileInputStream(filePath)) {
			JsonReader reader = Json.createReader(fis);
			JsonArray imagesArray = reader.readObject().getJsonArray("images");
			for (JsonValue value : imagesArray) {
				processImage(value.asJsonObject());
			}
			if (!images.isEmpty()) {
				currentImageIndex = 0;
				updateDisplay();
			}
		} catch (Exception e) {
			LOG.error("Failed to load images from file: {}", filePath, e);
			JOptionPane.showMessageDialog(frame, "Failed to load images: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Loads images from a JSON file path provided.
	 * 
	 * @param filePath The path to the JSON file containing image data.
	 */
	public void loadImagesFromJsonLarge(String fileText) {
		try {
			JsonReader jsonReader = Json.createReader(new StringReader(fileText));
			JsonObject mainObject = jsonReader.readObject().asJsonObject();
			JsonObject dataObject = mainObject.getJsonObject("data");
			JsonArray tableArray = dataObject.getJsonArray("data");
			for (JsonValue table : tableArray) {
				JsonObject tableObject = table.asJsonObject();
				if (tableObject.getString("tableName").equals("characters")) {
					JsonArray rows = tableObject.get("rows").asJsonArray();
					for (JsonValue rowValue : rows) {
						JsonObject rowObject = rowValue.asJsonObject();
						/*
						 * AI Character Images.
						 */
						JsonObject avatarObject = rowObject.getJsonObject("avatar");
						String image = avatarObject.getString("url");
						if (image.isBlank()) {
							LOG.warn("Image is blank.  Moving on.");
							continue;
						}
						processImage(image);

						JsonObject userObject = rowObject.getJsonObject("userCharacter");
						LOG.info("The User Object is {}", userObject.toString());
						JsonObject userAvatarObject = userObject.getJsonObject("avatar");
						if (userAvatarObject.isEmpty()) {
							LOG.warn("The avatar object is empty.");
							continue;
						}
						LOG.info("User Avatar object is {}", userAvatarObject.toString());
						String userImage = userAvatarObject.getString("url");
						LOG.info("User MIME is {}", userImage);
						if (userImage.isBlank()) {
							LOG.warn("User Image is blank.  Moving on.");
							continue;
						}
						processImage(userImage);
					}
				}
			}
			if (!images.isEmpty()) {
				currentImageIndex = 0;
				updateDisplay();
			}
		} catch (Exception e) {
			LOG.error("Failed to load images from file. {}", e);
			JOptionPane.showMessageDialog(frame, "Failed to load images: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void processImage(JsonObject imageObject) {
		String base64Data = imageObject.getString("data");
		byte[] imageBytes = Base64.getDecoder().decode(base64Data);
		if (isUniqueImage(imageBytes)) {
			try {
				images.add(ImageIO.read(new ByteArrayInputStream(imageBytes)));
			} catch (IOException e) {
				LOG.error("Failed to decode image: {}", e.getMessage());
			}
		}
	}

	private void processImage(String image) {
		if (image.isBlank()) {
			LOG.error("A BLANK IMAGE??? WTF!!!!!!!????");
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

	private boolean isUniqueImage(byte[] imageBytes) {
		String imgString = Arrays.toString(imageBytes);
		if (!imageSet.contains(imgString)) {
			LOG.info("Image is unique.");
			imageSet.add(imgString);
			return true;
		}
		return false;
	}

	private void showPreviousImage() {
		if (currentImageIndex > 0) {
			currentImageIndex--;
		} else {
			currentImageIndex = (images.size() - 1);
		}
		updateDisplay();
	}

	private void showNextImage() {
		if (currentImageIndex < images.size() - 1) {
			currentImageIndex++;
		} else {
			currentImageIndex = 0;
		}
		updateDisplay();
	}

	private void updateDisplay() {
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

	private void updateInfoLabel() {
		infoLabel.setText(
				String.format("Image %d of %d (%d unique)", currentImageIndex + 1, images.size(), imageSet.size()));
	}

	private void updateFrameSize() {
		if (currentImageIndex >= 0) {
			int width = images.get(currentImageIndex).getWidth();
			int height = images.get(currentImageIndex).getHeight();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setSize(Math.min(width, screenSize.width), Math.min(height + PADDING, screenSize.height));
			frame.revalidate();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ImageViewer viewer = new ImageViewer();
			if (args.length > 0) {
				viewer.loadImagesFromJsonLarge(args[0]);
			} else {
				LOG.info("No filename specified. Using default.");
				viewer.loadImagesFromJsonLarge(DEFAULT_IMAGE_FILE);
			}
		});
	}
}

package org.kcs.chatdisplay;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kcs.chatdisplay.model.Archive;
import org.kcs.chatdisplay.model.Avatar;
import org.kcs.chatdisplay.model.Character;
import org.kcs.chatdisplay.model.CharacterData;
import org.kcs.chatdisplay.model.Data;
import org.kcs.chatdisplay.model.TableData;

import com.google.gson.Gson;

/**
 * ImageViewer class for displaying images from JSON-encoded MIME data. This
 * viewer handles image navigation and display within a Swing-based UI.
 */
public class GsonImageViewer extends AbstractImageViewer{
	private static final Logger LOG = LogManager.getLogger(GsonImageViewer.class);

	/**
	 * Constructor initializes the UI for image viewing.
	 */
	public GsonImageViewer() {
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
	@Override
	public void loadImagesFromJson(String fileText) {
		try {
			Gson gson = new Gson();
			Archive archive = gson.fromJson(fileText, Archive.class);
			Data data = archive.getData();
			List<CharacterData> characterDatas = data.getData();
			
			for (CharacterData characterData : characterDatas) {
				if (characterData.getTableName().equals("characters")) {
					Character[] characters = characterData.getRows();
					for (Character character : characters) {
						/*
						 * AI Character Images.
						 */
						Avatar avatar = character.getAvatar();
						String image = avatar.getUrl();
						if (image.isBlank()) {
							LOG.warn("Image is blank.  Moving on.");
							continue;
						}
						processImage(image);

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

	private void processImage(String image) {
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

package org.kcs.chatdisplay;

import java.awt.image.BufferedImage;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kcs.chatdisplay.model.Archive;
import org.kcs.chatdisplay.model.Avatar;
import org.kcs.chatdisplay.model.Character;
import org.kcs.chatdisplay.model.CharacterData;
import org.kcs.chatdisplay.model.Data;

import com.google.gson.Gson;

/**
 * ImageViewer class for displaying images from JSON-encoded MIME data. This
 * viewer handles image navigation and display within a Swing-based UI.
 */
public class GsonImageViewer extends AbstractImageViewer {
	private static final Logger LOG = LogManager.getLogger(GsonImageViewer.class);

	/**
	 * Loads images from a JSON file path provided.
	 * 
	 * @param filePath The path to the JSON file containing image data.
	 */
	@Override
	public List<BufferedImage> loadImagesFromJson(String fileText) {
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
		return images;
	}
}

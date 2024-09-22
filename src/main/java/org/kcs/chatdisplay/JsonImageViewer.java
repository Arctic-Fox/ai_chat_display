package org.kcs.chatdisplay;
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
import java.awt.image.BufferedImage;
import java.io.StringReader;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ImageViewer class for displaying images from JSON-encoded MIME data. This
 * viewer handles image navigation and display within a Swing-based UI.
 */
public class JsonImageViewer extends AbstractImageViewer{
	private static final Logger LOG = LogManager.getLogger(JsonImageViewer.class);

	/**
	 * Loads images from a JSON file path provided.
	 * 
	 * @param filePath The path to the JSON file containing image data.
	 */
	@Override
	public List<BufferedImage> loadImagesFromJson(String fileText) {
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

		} catch (Exception e) {
			LOG.error("Failed to load images from file. {}", e);
		}
		
		return images;
	}
}

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
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

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

	protected void processImage(String mimeImage) {
		if (mimeImage.isBlank()) {
			LOG.error("Image is blank.");
			return;
		}
		mimeImage = mimeImage.substring(23); //Remove leading metadata
		try {
			byte[] imageBytes = Base64.getDecoder().decode(mimeImage);
			if (isUniqueImage(imageBytes)) {
				images.add(ImageIO.read(new ByteArrayInputStream(imageBytes)));
			}
		} catch (Exception e) {
			LOG.error("Failed to decode image: {}", e.getMessage());
		}
	}
}

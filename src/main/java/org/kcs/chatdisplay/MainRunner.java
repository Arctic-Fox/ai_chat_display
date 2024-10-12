package org.kcs.chatdisplay;
import java.awt.image.BufferedImage;
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
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kcs.chatdisplay.ui.MainUi;
import org.kcs.chatdisplay.util.GzipExtractor;
import org.kcs.chatdisplay.util.JsonExtractorFactory;
import org.kcs.chatdisplay.util.JsonExtractorType;
import org.kcs.chatdisplay.util.PropertyRetriever;
import org.kcs.chatdisplay.util.Utilities;  

public class MainRunner {
	private static final Logger LOG = LogManager.getLogger();
	
	private static final JsonExtractorType EXTRACTOR = JsonExtractorType.GSON;

	public static void main(String[] args) {

		File chosenFile = openFileChooser();
		List<File> fileList = new ArrayList<>();
		if (chosenFile.isDirectory()) {
			LOG.info("Selected file {} is a directory.", chosenFile.getName());
			for (File file : chosenFile.listFiles()) {
				if(file.getName().endsWith("json.gz")) {
					LOG.info("Adding file {} to list.", file.getName());
					fileList.add(file);
				}
			}
		} else {
			LOG.info("Processing single file {}.", chosenFile.getName());
			fileList.add(chosenFile);
		}
		
		LOG.info("File list has {} files in it.", fileList.size());
		
		processFileList(fileList);
		MainUi ui = new MainUi();
	}
	
	private static void processFileList(List<File> fileList) {
		LOG.info("Processing {} file(s).", fileList.size());
		GzipExtractor extractor = new GzipExtractor();
		for(File file : fileList) {
			LOG.info("Extracting file {}.", file.getName());
			String fileText = extractor.extractGzip(file.getAbsolutePath());
			if(fileText.isBlank()) {
				LOG.error("There was a problem opening the archive file.");
			}
			LOG.info("File {} extracted", file.getName());
			getImagesFromFile(fileText);
		}
	}
	
	private static void getImagesFromFile(String fileText) {
		AbstractImageViewer viewer = JsonExtractorFactory.getExtractor(EXTRACTOR);

        try {
			viewer.loadImagesFromJson(fileText);
		} catch (Exception e) {
			LOG.error("Unable to build image list.", e);
		}
	}
	
    private static File openFileChooser() {
		PropertyRetriever props = new PropertyRetriever();
		String startDirectory = props.getProperty("start.directory");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(startDirectory));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("GZip files", "gz");
//        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile;
        } else {
            return null;
        }
    }
}

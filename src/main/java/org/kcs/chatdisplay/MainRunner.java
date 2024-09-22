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
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kcs.chatdisplay.ui.MainUi;
import org.kcs.chatdisplay.util.GzipExtractor;
import org.kcs.chatdisplay.util.PropertyRetriever;  

public class MainRunner {
	private static final Logger LOG = LogManager.getLogger();

	public static void main(String[] args) {
		GzipExtractor extractor = new GzipExtractor();
		String fileName = openFileChooser().getAbsolutePath();
		String fileText = extractor.extractGzip(fileName);
		if(fileText.isBlank()) {
			LOG.error("There was a problem opening the archive file.");
			return;
		}
		LOG.info("File {} extracted", fileName);
		
		MainUi ui = new MainUi(fileText);
	} 
	
    private static File openFileChooser() {
		PropertyRetriever props = new PropertyRetriever();
		String startDirectory = props.getProperty("start.directory");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(startDirectory));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("GZip files", "gz");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile;
        } else {
            return null;
        }
    }
}

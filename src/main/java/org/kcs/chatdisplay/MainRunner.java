package org.kcs.chatdisplay;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kcs.chatdisplay.ui.MainUi;
import org.kcs.chatdisplay.util.GzipExtractor;  

public class MainRunner {
	private static final Logger LOG = LogManager.getLogger();

	public static void main(String[] args) {
		GzipExtractor extractor = new GzipExtractor();
		String fileName = openFileChooser().getAbsolutePath();
		String fileText = extractor.extractGzip(fileName);
		LOG.info("File {} extracted", fileName);
		
		MainUi ui = new MainUi(fileText);
	} 
	
    private static File openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("C:/"));
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

package org.kcs.chatdisplay.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GzipExtractor {
	private static final Logger LOG = LogManager.getLogger();
	
    public String extractGzip(String filePath) {
    	LOG.info("File to extract: {}", filePath);
        try {
        	FileInputStream inputStream = new FileInputStream(filePath);
            GZIPInputStream gis = new GZIPInputStream(inputStream);
            InputStreamReader isr = new InputStreamReader(gis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            gis.close();
            inputStream.close();
            return sb.toString();
        } catch (FileNotFoundException e) {
        	LOG.error("Unable to find file {}", filePath);
            return "The file was lost at sea, Captain.";
        } catch (IOException e) {
        	LOG.error("Unable to open file.");
            return "Arrr, there be a storm in the data stream!";
        }
    }
}
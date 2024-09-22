package org.kcs.chatdisplay.util;
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
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GzipExtractor {
	private static final Logger LOG = LogManager.getLogger();
	
    public String extractGzip(String filePath) {
    	LOG.info("File to extract: {}", filePath);
        try {
        	FileInputStream fileInputStream = new FileInputStream(filePath);
            GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
            InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
            	stringBuilder.append(line);
            }
            bufferedReader.close();
            gzipInputStream.close();
            fileInputStream.close();
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
        	LOG.error("Unable to find file {}", filePath);
            return "";
        } catch (IOException e) {
        	LOG.error("Unable to open file.");
            return "";
        }
    }
}
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyRetriever {

	private static final Logger LOG = LogManager.getLogger();
	private static final String PROPS_FILE = "config.properties";
	
	public String getProperty(String property) {
		Properties properties = new Properties();
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPS_FILE);) {
			properties.load(inputStream);
		} catch (FileNotFoundException ex) {
		    LOG.error("Cannot find properties file: {}", PROPS_FILE);
		} catch (IOException ex) {
		    LOG.error("Unable to read properties file.");
		}
		return properties.getProperty(property);
	}
	
}

package com.autotest.configure.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationService {
	private static final String DEFAULT_CONFIGE_FILE_PATH = "";
	private static Properties prop;
	
	private ConfigurationService() {
		throw new IllegalStateException("ConfigurationService Class");
	}

	public static void loadConfiguration(String filePath) throws IOException {
		FileInputStream input = new FileInputStream(filePath);
		prop = new Properties();
		prop.load(input);
	}

	public static void loadConfiguration() throws IOException {
		FileInputStream input = new FileInputStream(DEFAULT_CONFIGE_FILE_PATH);
		prop = new Properties();
		prop.load(input);
	}
	
	public static String getValue(String key) {
		return prop.getProperty(key);
	}
}

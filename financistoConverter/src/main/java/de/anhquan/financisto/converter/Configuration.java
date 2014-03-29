package de.anhquan.financisto.converter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	
	static Properties config;
	
	public static String RULE_FILE = "rule-file";
	public static String FINANCISTO_ACCOUNT = "financisto-account";
	public static String FINANCISTO_PROJECT = "financisto-project";
	public static String DEFAULT_INPUT_FILE = "default-input-file";
	public static String DEFAULT_CSV_OUTPUT_FILE = "default-csv-output-file";
	
	
	public Configuration(String configFile){
		config = new Properties();
		try {
			config.load(new FileInputStream(configFile));
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find the configuration file '"+configFile+"'. errmsg: "+e.getMessage());
		} catch (IOException e) {
			System.out.println("Error encountered during parsing configuration file '"+configFile+"'. errmsg: "+e.getMessage());
		}
	}

	public String get(String key) {
		return config.getProperty(key);
	}
}

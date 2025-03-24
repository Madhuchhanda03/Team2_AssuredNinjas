package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	// static Properties prop;

	public static String readDataFromConfig(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("./src/test/resources/testData/config.properties");
		prop.load(file);
		// System.out.println(prop);
		return prop.getProperty(key);

	}

}

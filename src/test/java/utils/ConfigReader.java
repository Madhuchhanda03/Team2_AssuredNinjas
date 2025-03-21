package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	static Properties prop;

	public static void readDataFromConfig() throws IOException {
		prop = new Properties();
		FileInputStream file = new FileInputStream("./src/test/resources/testData/config.properties");
		prop.load(file);
		System.out.println(prop);

	}

	public static String getBaseUrl() {
		String baseUrl= prop.getProperty("baseUrl");
		return baseUrl;

	}
	public static String getUsername() {
		  String username=prop.getProperty("userLoginEmailId");
		 return username;
	  }  
	  
	  public static String getpassword() {
		  String password=prop.getProperty("password");
		 return password;
	  }
	
}

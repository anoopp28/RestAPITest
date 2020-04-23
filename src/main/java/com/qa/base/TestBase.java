package com.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class TestBase {
	
	
	public int RESPONSE_STATUS_CODE_200 = 200; 
	public int RESPONSE_STATUS_CODE_500 = 500; 
	public int RESPONSE_STATUS_CODE_400 = 400; 
	public int RESPONSE_STATUS_CODE_404 = 404; 
	public static int RESPONSE_STATUS_CODE_201 = 201; 
	
	
	
	//WebDriver Properties
		
		public static Properties prop;
		
		//Constructor to initialize properties
		public TestBase() {
			try {
				prop = new Properties();
				FileInputStream fis = new FileInputStream("C:\\Users\\52001551\\eclipse-workspace\\RestAPITest\\src\\main\\java\\com\\qa\\config\\config.properties");
				
				prop.load(fis);
			} catch (IOException e) {
				e.getMessage();
			}
				
			}


}

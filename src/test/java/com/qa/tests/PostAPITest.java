package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.Users;

public class PostAPITest extends TestBase {

	
	String serviceurl;
	String uri;
	String url;
	CloseableHttpResponse closeablehttpResponse;
	
	
	public PostAPITest() {
		super();
	}
	
	@BeforeMethod
	public void setup()  {
		url = prop.getProperty("url");
		serviceurl = prop.getProperty("serviceurl");
		uri = url+serviceurl;	
	}
	
	
	@Test
	public void PostAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		RestClient restClient = new RestClient();    //To Access the get method inside RestClient class
		
		//To Pass the Headers
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		
		//Jackson API to covert POJO to JSON
		ObjectMapper mapper = new ObjectMapper();
		
		Users users = new Users("morpheus", "Leader");
		
		
		//Converting POJO object(Users.java reference) to JSON and store it in new Json file - users.json
		mapper.writeValue(new File("C:\\Users\\52001551\\eclipse-workspace\\RestAPITest\\src\\main\\java\\com\\qa\\util\\Users.json"), users);
		
		
		//Convert object to Json in String  - MARSHILLING
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println("User Json String  "+usersJsonString);
		
		System.out.println("-------------------------------------------------------");
		
		
		
		
		//This method will hit the API and response is stores in closeablehttpResponse
		closeablehttpResponse = restClient.post(uri, usersJsonString , headerMap );
		
		
		
		
		System.out.println("-------------------------------------------------------");
		
		//1. StatusCode
		int StatusCode = closeablehttpResponse.getStatusLine().getStatusCode();
		System.out.println("StatusCode in Response is "+StatusCode);
		Assert.assertEquals(StatusCode, TestBase.RESPONSE_STATUS_CODE_201);
		
		
		
		
		//2. Json String
		String responseString = EntityUtils.toString(closeablehttpResponse.getEntity(), "UTF-8");
		System.out.println("Response String  "+responseString);
		
		
		System.out.println("-------------------------------------------------------");
		
		
		
		
		//3. response string to JSON
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response Json "+responseJson);
		
		System.out.println("-------------------------------------------------------");
		
		
		
		//Validation of response
	    // to validate we convert from JSON to java object - UNMARSHILLING
		Users userRespobj = mapper.readValue(responseString, Users.class);
		System.out.println(userRespobj);
		
		System.out.println("-------------------------------------------------------");
		
		
		Assert.assertTrue(users.getName().equals(userRespobj.getName()));
		
		Assert.assertTrue(users.getJob().equals(userRespobj.getJob()));
		
		
		
	}
	
	
}

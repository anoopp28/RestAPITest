package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	
	String serviceurl;
	String uri;
	String url;
	CloseableHttpResponse closeablehttpResponse;
	
	public GetAPITest() {
		super();
	}

	@BeforeMethod
	public void setup()  {
		url = prop.getProperty("url");
		serviceurl = prop.getProperty("serviceurl");
		uri = url+serviceurl;	
	}
	
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
	RestClient restClient = new RestClient();    //To Access the get method inside RestClient class
	closeablehttpResponse = restClient.getAPI(uri);
	
	
	
	 //method to get the status code
    int statusCode = closeablehttpResponse.getStatusLine().getStatusCode();     
    System.out.println("StatusCode: "+statusCode);
    Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "StatusCode is not 200");
  
    System.out.println("-------------------------------------------------------");
    
    
    
    //To get the entire response string
    String responseString =EntityUtils.toString(closeablehttpResponse.getEntity(),"UTF-8");   
    System.out.println("response string from API ----> "+responseString);
    
    System.out.println("-------------------------------------------------------");
    
    
    
    //TO convert the string to JSON
    JSONObject responseJson = new JSONObject(responseString);
    System.out.println("JSON response from API ----> "+responseJson);
    
    System.out.println("-------------------------------------------------------");
    
    
    
    //From the json response, to get the particular value we use this utility passing responseJson and particular object with /
    String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");  
    System.out.println("Value of per page----> "+perPageValue);
   Assert.assertEquals(Integer.parseInt(perPageValue), 6);         //Coz getByJpath will return string value
    
    System.out.println("-------------------------------------------------------");
    
    
    
    //To get the data from JSON Array
    String lastname = TestUtil.getValueByJPath(responseJson, "data[1]/last_name");  
    String id = TestUtil.getValueByJPath(responseJson, "data[1]/id"); 
    String avatar = TestUtil.getValueByJPath(responseJson, "data[1]/avatar"); 
    String firstname = TestUtil.getValueByJPath(responseJson, "data[1]/first_name"); 
    	
    
    System.out.println(lastname);
    System.out.println(id);
    System.out.println(avatar);
    System.out.println(firstname);
    
    
    System.out.println("-------------------------------------------------------");
    
    // To get all the headers and are stored in Header Array
    Header[] headersArray = closeablehttpResponse.getAllHeaders();
    
    //Create HashMap object to store the header array to HashMap
    HashMap<String, String> allheaders = new HashMap<String, String>();
    
    for(Header header: headersArray) {
    	allheaders.put(header.getName(), header.getValue());
    }
    
    
    System.out.println("Headers Array --> "+allheaders);
    
	}	
	
	
	
	@Test
	public void getAPIwithHeaders() throws ClientProtocolException, IOException {
		RestClient restClient = new RestClient();    //To Access the get method inside RestClient class
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		
		closeablehttpResponse = restClient.getAPIwithHeaders(uri,headerMap);
		
		
		
		 //method to get the status code
	    int statusCode = closeablehttpResponse.getStatusLine().getStatusCode();     
	    System.out.println("StatusCode: "+statusCode);
	    Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "StatusCode is not 200");
	  
	    System.out.println("-------------------------------------------------------");
	    
	    
	    
	    //To get the entire response string
	    String responseString =EntityUtils.toString(closeablehttpResponse.getEntity(),"UTF-8");   
	    System.out.println("response string from API ----> "+responseString);
	    
	    System.out.println("-------------------------------------------------------");
	    
	    
	    
	    //TO convert the string to JSON
	    JSONObject responseJson = new JSONObject(responseString);
	    System.out.println("JSON response from API ----> "+responseJson);
	    
	    System.out.println("-------------------------------------------------------");
	    
	    
	    
	    //From the json response, to get the particular value we use this utility passing responseJson and particular object with /
	    String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");  
	    System.out.println("Value of per page----> "+perPageValue);
	   Assert.assertEquals(Integer.parseInt(perPageValue), 6);         //Coz getByJpath will return string value
	    
	    System.out.println("-------------------------------------------------------");
	    
	    
	    
	    //To get the data from JSON Array
	    String lastname = TestUtil.getValueByJPath(responseJson, "data[1]/last_name");  
	    String id = TestUtil.getValueByJPath(responseJson, "data[1]/id"); 
	    String avatar = TestUtil.getValueByJPath(responseJson, "data[1]/avatar"); 
	    String firstname = TestUtil.getValueByJPath(responseJson, "data[1]/first_name"); 
	    	
	    
	    System.out.println(lastname);
	    System.out.println(id);
	    System.out.println(avatar);
	    System.out.println(firstname);
	    
	    
	    System.out.println("-------------------------------------------------------");
	    
	    // To get all the headers and are stored in Header Array
	    Header[] headersArray = closeablehttpResponse.getAllHeaders();
	    
	    //Create HashMap object to store the header array to HashMap
	    HashMap<String, String> allheaders = new HashMap<String, String>();
	    
	    for(Header header: headersArray) {
	    	allheaders.put(header.getName(), header.getValue());
	    }
	    
	    
	    System.out.println("Headers Array --> "+allheaders);
	    
	    
	   //To check the header values
	    String ServerValue = allheaders.get("Server");
	    Assert.assertEquals(ServerValue, "cloudflare");
		}	
	
	
	
	
	
	
}

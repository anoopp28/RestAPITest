package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	
	//1. GET method
	public  CloseableHttpResponse getAPI(String uri) throws ClientProtocolException, IOException {
		
		CloseableHttpClient HttpClient = HttpClients.createDefault(); //Will create client connection
	    HttpGet httpget = new HttpGet(uri);      //This is HTTP request
	    CloseableHttpResponse closeablehttpResponse = HttpClient.execute(httpget);     //This is like clicking on send to hit the HttpGet API where uri is passed 
	    //Entire response from API will be stored in closeablehttpResponse reference
	    
	    return closeablehttpResponse;
	    
	}
	
	//2. Get method with Headers
     public  CloseableHttpResponse getAPIwithHeaders(String uri, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient HttpClient = HttpClients.createDefault(); //Will create client connection
	    HttpGet httpget = new HttpGet(uri);      //This is HTTP Get request
	    
	    for(Map.Entry<String, String> entry : headerMap.entrySet()) {
	    	httpget.addHeader(entry.getKey(), entry.getValue());
	    }
	    
	    CloseableHttpResponse closeablehttpResponse = HttpClient.execute(httpget);     //This is like clicking on send to hit the HttpGet API where uri is passed 
	    //Entire response from API will be stored in closeablehttpResponse reference
	    
	     return closeablehttpResponse;
	    
	    
	}
     
     //3. Post Method
	  public CloseableHttpResponse post(String uri, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {

		  CloseableHttpClient HttpClient = HttpClients.createDefault(); //Will create client connection
		    HttpPost httppost = new  HttpPost(uri);      //This is HTTP request 
		  
		    httppost.setEntity(new StringEntity(entityString));    //This is for json payload
		    
		    for(Map.Entry<String, String> entry : headerMap.entrySet()) {
		    	httppost.addHeader(entry.getKey(), entry.getValue());
		    
		    }
		    CloseableHttpResponse closeablehttpResponse = HttpClient.execute(httppost);   
		    
		    return closeablehttpResponse;
	  
	  }
		    
	
}

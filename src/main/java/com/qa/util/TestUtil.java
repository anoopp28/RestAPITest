package com.qa.util;

import org.json.JSONObject;
import org.json.JSONArray;

public class TestUtil {
	
	public static JSONObject responsejson;     //This contains JSON response 
	
	public static String getValueByJPath(JSONObject responsejson, String jpath){
		Object obj = responsejson;
		for(String s : jpath.split("/")) 
			if(!s.isEmpty()) 
				if(!(s.contains("[") || s.contains("]")))
					obj = ((JSONObject) obj).get(s);
				else if(s.contains("[") || s.contains("]"))
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
		return obj.toString();

}

}






//{
//	"per_page": 6, -------------Single
//	"total": 12,
//	"ad": {
//		"company": "StatusCode Weekly",
//		"text": "A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things.",
//		"url": "http://statuscode.org/"
//	},
//	"data": [{    ---------------Arrray starts
//		"last_name": "Bluth",
//		"id": 1,
//		"avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg",
//		"first_name": "George",
//		"email": "george.bluth@reqres.in"
//	}, {
//		"last_name": "Weaver",
//		"id": 2,
//		"avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg",
//		"first_name": "Janet",
//		"email": "janet.weaver@reqres.in"
//	}, {
//		"last_name": "Wong",
//		"id": 3,
//		"avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/olegpogodaev/128.jpg",
//		"first_name": "Emma",
//		"email": "emma.wong@reqres.in"
//	}, {
//		"last_name": "Holt",
//		"id": 4,
//		"avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/marcoramires/128.jpg",
//		"first_name": "Eve",
//		"email": "eve.holt@reqres.in"
//	}, {
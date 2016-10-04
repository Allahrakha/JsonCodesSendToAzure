package util;

import java.util.*;
import java.io.*;



import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.*;



public class AnalyticsReport {
	
	
	    public static String apikey;
	    public static String apiurl;
	    public static String jsonBody;
	

	    public static void readJson(String filename) {
	        try {
	            File apiFile = new File(filename);
	            Scanner sc = new Scanner(apiFile);
	            jsonBody = "";
	            while (sc.hasNext()) {
	                jsonBody += sc.nextLine()+"\n";
	            }
	        }
	        catch (Exception e){
	            System.out.println(e.toString());
	        }
	    }
	
	
	    public static void readApiInfo(String filename) {
	        
	        try {
	            File apiFile = new File(filename);
	            Scanner sc = new Scanner(apiFile);
	            
	            apiurl = sc.nextLine();
	            apikey = sc.nextLine();
	            
	        }
	        catch (Exception e){
	            System.out.println(e.toString());
	        }
	        
	    }
	    
	    public static String rrsHttpPost() {
	        
	        HttpPost post;
	        HttpClient client;
	        StringEntity entity;
	        
	        try {
	            // create HttpPost and HttpClient object
	            post = new HttpPost(apiurl);
	            client = HttpClientBuilder.create().build();
	            
	            // setup output message by copying JSON body into 
	            // apache StringEntity object along with content type
	            entity = new StringEntity(jsonBody, HTTP.UTF_8);
	            entity.setContentEncoding(HTTP.UTF_8);
	            entity.setContentType("text/json");

	            // add HTTP headers
	            post.setHeader("Accept", "text/json");
	            post.setHeader("Accept-Charset", "UTF-8");
	        
	            // set Authorization header based on the API key
	            post.setHeader("Authorization", ("Bearer "+apikey));
	            post.setEntity(entity);

	            // Call REST API and retrieve response content
	            HttpResponse authResponse = client.execute(post);
	            
	            return EntityUtils.toString(authResponse.getEntity());
	            
	        }
	        catch (Exception e) {
	            
	            return e.toString();
	        }
	    
	    }
	
	
	    public static void main(String[] args) {
	        // check for mandatory argments. This program expects 2 arguments 
	        // first argument is full path with file name of JSON file and 
	        // second argument is full path with file name of API file that contains API URL and API Key of request response REST API
	    
	        try {
			
	                // read JSON file name
	        	
	        
	        	
	        String jsonFile = "d://data//rrsJson.json";
	        
	        
	        
	        
	        
	                // read API file name
			String apiFile =  "d://data//apiInfo.txt";
	            
	                // call method to read API URL and key from API file
	        readApiInfo(apiFile);
	                
	                // call method to read JSON input from the JSON file
			readJson(jsonFile);
	                
	                // print the response from REST API
			System.out.println(rrsHttpPost());
	        }
	        catch (Exception e) {
	            System.out.println(e.toString());
	        }
	    }
	    

}

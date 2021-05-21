package com.hanul.justdoeat.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Foodimg {

	 String clientId = "0aK5eyZdZ_F_RnzpFpKn"; //
     String clientSecret = "3zSA1Wbj9j"; //
     
     String text;

	public String requstAPI(String food) {
		
    	 try {
	            text = URLEncoder.encode(food, "UTF-8");
//	            text = food;
	            System.out.println("음식 : " + text);
	       } catch (UnsupportedEncodingException e) {
	           throw new RuntimeException("한글오류",e);
      }
	      String apiURL = "https://openapi.naver.com/v1/search/image?query=" + text
	    		  + "&display=" + 1;// json 寃곌낵`
	        
	        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 
	        
	        Map<String, String> requestHeaders = new HashMap<String, String>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	        String responseBody = get(apiURL,requestHeaders);
	        
	        System.out.println("결과 : " + responseBody);

	       return responseBody;
    	 
    	 
     }
     
     public String get(String apiUrl, Map<String, String> requestHeaders) {
    	  HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }

	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 
	                return readBody(con.getInputStream());
	            } else { //
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 에러", e);
	        } finally {
	            con.disconnect();
	        }
     }
     
     public HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URLd에러: " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("에러 " + apiUrl, e);
	        }
	    }
     
     public String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);

	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();

	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }

	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 에러", e);
	        }
	    }

}

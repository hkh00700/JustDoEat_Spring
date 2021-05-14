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

	 String clientId = "0aK5eyZdZ_F_RnzpFpKn"; //�븷�뵆由ъ��씠�뀡 �겢�씪�씠�뼵�듃 �븘�씠�뵒媛�"
     String clientSecret = "APQtJbbpUG"; //�븷�뵆由ъ��씠�뀡 �겢�씪�씠�뼵�듃 �떆�겕由욧컪"
     
     String text;

	public String requstAPI(String food) {
		
    	 try {
	            text = URLEncoder.encode(food, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("寃��깋�뼱 �씤肄붾뵫 �떎�뙣",e);
	        }
	        String apiURL = "https://openapi.naver.com/v1/search/image?query=" + text
	        		+ "&display=" + 1;// json 寃곌낵
	        
	        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 寃곌낵
	        
	        Map<String, String> requestHeaders = new HashMap<String, String>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	        String responseBody = get(apiURL,requestHeaders);
	        
	        System.out.println("�쓳�떟 : " + responseBody);

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
	            if (responseCode == HttpURLConnection.HTTP_OK) { // �젙�긽 �샇異�
	                return readBody(con.getInputStream());
	            } else { // �뿉�윭 諛쒖깮
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API �슂泥�怨� �쓳�떟 �떎�뙣", e);
	        } finally {
	            con.disconnect();
	        }
     }
     
     public HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL�씠 �옒紐삳릺�뿀�뒿�땲�떎. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("�뿰寃곗씠 �떎�뙣�뻽�뒿�땲�떎. : " + apiUrl, e);
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
	            throw new RuntimeException("API �쓳�떟�쓣 �씫�뒗�뜲 �떎�뙣�뻽�뒿�땲�떎.", e);
	        }
	    }

}

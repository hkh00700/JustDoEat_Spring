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

	 String clientId = "0aK5eyZdZ_F_RnzpFpKn"; //占쎈막占쎈탣�뵳�딉옙占쎌뵠占쎈�� 占쎄깻占쎌뵬占쎌뵠占쎈섧占쎈뱜 占쎈툡占쎌뵠占쎈탵揶쏉옙"
     String clientSecret = "3zSA1Wbj9j"; //占쎈막占쎈탣�뵳�딉옙占쎌뵠占쎈�� 占쎄깻占쎌뵬占쎌뵠占쎈섧占쎈뱜 占쎈뻻占쎄쾿�뵳�슙而�"
     
     String text;

	public String requstAPI(String food) {
		
    	 try {
	            text = URLEncoder.encode(food, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("野껓옙占쎄퉳占쎈선 占쎌뵥�굜遺얜뎃 占쎈뼄占쎈솭",e);
	        }
	        String apiURL = "https://openapi.naver.com/v1/search/image?query=" + text
	        		+ "&display=" + 1;// json 野껉퀗�궢
	        
	        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 野껉퀗�궢
	        
	        Map<String, String> requestHeaders = new HashMap<String, String>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	        String responseBody = get(apiURL,requestHeaders);
	        
	        System.out.println("占쎌벓占쎈뼗 : " + responseBody);

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
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 占쎌젟占쎄맒 占쎌깈�빊占�
	                return readBody(con.getInputStream());
	            } else { // 占쎈퓠占쎌쑎 獄쏆뮇源�
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 占쎌뒄筌ｏ옙�⑨옙 占쎌벓占쎈뼗 占쎈뼄占쎈솭", e);
	        } finally {
	            con.disconnect();
	        }
     }
     
     public HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL占쎌뵠 占쎌삋筌륁궠由븝옙肉�占쎈뮸占쎈빍占쎈뼄. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("占쎈염野껉퀣�뵠 占쎈뼄占쎈솭占쎈뻥占쎈뮸占쎈빍占쎈뼄. : " + apiUrl, e);
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
	            throw new RuntimeException("API 占쎌벓占쎈뼗占쎌뱽 占쎌뵭占쎈뮉占쎈쑓 占쎈뼄占쎈솭占쎈뻥占쎈뮸占쎈빍占쎈뼄.", e);
	        }
	    }

}

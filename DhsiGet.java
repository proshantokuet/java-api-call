package com.non.jom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;


import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import org.springframework.http.HttpMethod;

import com.mysql.jdbc.StringUtils;

public class DhsiGet {

	/**
	 * @param args
	 * @throws JSONException 
	 * @throws IOException 
	 */
	
	
	public static void main(String[] args) throws JSONException, IOException {
		
		
		JSONObject personObj =	new JSONObject();
		JSONArray nameArray =	new JSONArray();
		JSONObject nameAttrObj = new JSONObject();
		nameAttrObj.put("dataElement", "HYDA5S2jFz1");
		nameAttrObj.put("period", "201603");
		nameAttrObj.put("orgUnit", "Na2vHKtaYKL");
		nameAttrObj.put("value", "200");
		
		JSONObject nameAttrObj1 = new JSONObject();
		nameAttrObj1.put("dataElement", "GCwJmDGRN9a");
		nameAttrObj1.put("period", "201603");
		nameAttrObj1.put("orgUnit", "Na2vHKtaYKL");
		nameAttrObj1.put("value", "500");
		
		nameArray.put(nameAttrObj);
		nameArray.put(nameAttrObj1);
		personObj.put("dataValues", nameArray);
		
		
		
		
		//System.out.println(personObj.toString());
		
		
	TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
    };
    // Install the all-trusting trust manager
    SSLContext sc = null;
	try {
		sc = SSLContext.getInstance("SSL");
	} catch (NoSuchAlgorithmException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    try {
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
	} catch (KeyManagementException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

    // Create all-trusting host name verifier
    HostnameVerifier allHostsValid = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    // Install the all-trusting host verifier
    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
   
    
    try{
    	String url = "https://192.168.22.152/dhis/api/24/dataValueSets.json?dataSet=QQc3739XU2I&period=201604&orgUnit=Na2vHKtaYKL";
    	String charset = "UTF-8";
    	 String payload ="";
    	 String username = "dghs"; 
    	 String password = "Dghs@123";
        if(url.endsWith("/")){
        	url = url.substring(0, url.lastIndexOf("/"));
        }
        url = (url+(StringUtils.isEmptyOrWhitespaceOnly(payload)?"":("?"+payload))).replaceAll(" ", "%20");
    	URL urlo = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) urlo.openConnection();
		conn.setRequestProperty("Accept-Charset", charset);//Trojanhorse30
		boolean useBasicAuth = true;
		
		if(useBasicAuth){
			String encoded = new String(Base64.encodeBase64((username+":"+password).getBytes()));
	        conn.setRequestProperty("Authorization", "Basic "+encoded);
		}		
		conn.setRequestMethod(HttpMethod.GET.name());
        BufferedReader    br = new BufferedReader(new InputStreamReader((conn.getInputStream())));        
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
        sb.append(output);		        
        }
        System.out.println(sb.toString());	
    } catch (Exception e){           
    	e.printStackTrace();
    }
    /*String url = "https://192.168.22.152/dhis/api/24/dataValueSets.json?dataSet=QQc3739XU2I&period=201603&orgUnit=Na2vHKtaYKL";
	
	URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	
	String username = "dghs"; 
	 String password = "Dghs@123";
	con.setRequestMethod("GET");
	String encoded = new String(Base64.encodeBase64((username+":"+password).getBytes()));
    con.setRequestProperty("Authorization", "Basic "+encoded);
	
	con.setRequestProperty("User-Agent", "Mozilla/5.0");

	int responseCode = con.getResponseCode();
	System.out.println("\nSending 'GET' request to URL : " + url);
	System.out.println("Response Code : " + responseCode);

	BufferedReader in = new BufferedReader(
	        new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	}
	in.close();

	
	System.out.println(response.toString());*/
    

	}

}

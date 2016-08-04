package com.non.jom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;

import com.mysql.jdbc.StringUtils;

public class DhisRegisterEnroll {

	/**
	 * @param args
	 * @throws JSONException 
	 */
	
	
	public static void main(String[] args) throws JSONException {
		
		
		JSONObject personObj =	new JSONObject();
		
		personObj.put("trackedEntityInstance", "YcmNQibw4vF"); //reference from  trackedEntityInstances
		personObj.put("orgUnit", "Na2vHKtaYKL");
		personObj.put("program", "EqTR5vMOSzJ");
		personObj.put("enrollmentDate", "2016-08-03");
		personObj.put("incidentDate", "2016-08-03");
		
		
		
		
		System.out.println(personObj.toString());
		
		
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
    	String url = "https://192.168.22.152/dhis/api/enrollments";
        String payload ="";
        url = (url+(StringUtils.isEmptyOrWhitespaceOnly(payload)?"":("?"+payload))).replaceAll(" ", "%20");
    	URL urlo = new URL(url);
        HttpURLConnection urlc = (HttpURLConnection) urlo.openConnection();
        
        urlc.setRequestProperty("Content-Type", "application/json");
        String charset = "UTF-8";
        urlc.setRequestProperty("Accept-Charset", charset);
        String encoded = new String(Base64.encodeBase64(("dghs:Dghs@123").getBytes()));
        urlc.setRequestProperty("Authorization", "Basic "+encoded);
        urlc.setRequestMethod(HttpMethod.POST.name());          
        urlc.setFixedLengthStreamingMode(
        	personObj.toString().getBytes().length);           
        urlc.setDoOutput(true);
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(urlc.getOutputStream(), charset ), true); // true = autoFlush, important!
		writer.print(personObj.toString());
		  if (writer != null) writer.close();
	        int statusCode = urlc.getResponseCode();
	        if (statusCode != HttpURLConnection.HTTP_OK) {
	            
	        } 
	        BufferedReader    br = new BufferedReader(new InputStreamReader((urlc.getInputStream())));
	        StringBuilder sb = new StringBuilder();
	        String output;
	        while ((output = br.readLine()) != null) {
	        sb.append(output);		        
	        }
		System.out.println(sb.toString());			
    } catch (Exception e){           
    	e.printStackTrace();
    }
    
    

	}

}

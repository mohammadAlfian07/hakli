package com.sds.hakli.extension;

import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sds.hakli.pojo.BriApiToken;
import com.sds.hakli.pojo.BrivaCreateResp;
import com.sds.hakli.pojo.BrivaData;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class BriApiExt {
	
	String url_briapi = "https://sandbox.partner.api.bri.co.id/oauth/client_credential/accesstoken";
	String url_briva = "https://sandbox.partner.api.bri.co.id/v1/briva";
	String client_id = "P0HhD42Hk5zNQ8Me58gxRA9XIznRBQkN";
	String client_secret = "nUmPkHfO5qnxgZ9a";
	
	public static void main(String[] args) {
		try {
			BriApiExt api = new BriApiExt();
			//api.getToken();
			api.createBriva();
			//api.getBriva();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getToken() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String output = null;
		try {
			 // Create a trust manager that does not validate certificate chains
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
		    SSLContext sc = SSLContext.getInstance("SSL");
		    sc.init(null, trustAllCerts, new java.security.SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		    // Create all-trusting host name verifier
		    HostnameVerifier allHostsValid = new HostnameVerifier() {
				
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return false;
				}
			};

		    // Install the all-trusting host verifier
		    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		    
		    System.out.println("***Begin Access Token***");
			
		    MultivaluedMap<String, String> input = new MultivaluedHashMap<String, String>();
			input.add("client_id", client_id);
			input.add("client_secret", client_secret);
			
			Client client = Client.create();
			client.setConnectTimeout(60 * 1000);
			client.setReadTimeout(60 * 1000);

			WebResource webResource = client.resource(url_briapi);
			ClientResponse response = webResource.queryParam("grant_type", "client_credentials").type(MediaType.APPLICATION_FORM_URLENCODED)
					.post(ClientResponse.class, input);

			output = response.getEntity(String.class);
			System.out.println(output);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			BriApiToken obj = mapper.readValue(output, BriApiToken.class);
			client.destroy();
			System.out.println(obj.getAccess_token());
			System.out.println("***End Access Token***");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createBriva() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String output = null;
		try {
			 // Create a trust manager that does not validate certificate chains
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
		    SSLContext sc = SSLContext.getInstance("SSL");
		    sc.init(null, trustAllCerts, new java.security.SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		    // Create all-trusting host name verifier
		    HostnameVerifier allHostsValid = new HostnameVerifier() {
				
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return false;
				}
			};

		    // Install the all-trusting host verifier
		    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		    
		    System.out.println("***Begin Create BRIVA***");
			
		    BrivaData data = new BrivaData();
		    data.setAmount("100000");
		    data.setBrivaNo("77777");
		    data.setCustCode("6456454546");
		    data.setExpiredDate("2023-05-10 09:57:26");
		    data.setInstitutionCode("J104408");
		    data.setKeterangan("Retribusi oktober");
		    data.setNama("Ibrahim");
		    
		    Client client = Client.create();
			client.setConnectTimeout(60 * 1000);
			client.setReadTimeout(60 * 1000);
			
			String auth = "Bearer 6j0k44tKwLKD1fkkcgdAikHLR1gM";
			String xtimestamp = "2023-04-27T01:30:35.157Z";
			String jsonReq = mapper.writeValueAsString(data);
			
			StringBuffer payload = new StringBuffer();
			payload.append("path=/v1/briva");
			payload.append("&");
			payload.append("verb=POST");
			payload.append("&");
			payload.append("token=" + auth);
			payload.append("&");
			payload.append("timestamp=" + xtimestamp);
			payload.append("&");
			payload.append("body=" + jsonReq);
			
			System.out.println(payload.toString());
			String signature = encode(client_secret, payload.toString());
			System.out.println(signature);
			
			WebResource webResource = client.resource(url_briva);
			ClientResponse response = webResource.header("Authorization", auth)
					.header("BRI-Timestamp", xtimestamp)
					.header("BRI-Signature", signature)
					.type(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class, jsonReq);

			output = response.getEntity(String.class);
			System.out.println(output);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			//BrivaCreateResp obj = mapper.readValue(output, BrivaCreateResp.class);
			client.destroy();
			System.out.println("***End Create BRIVA***");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getBriva() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String output = null;
		try {
			 // Create a trust manager that does not validate certificate chains
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
		    SSLContext sc = SSLContext.getInstance("SSL");
		    sc.init(null, trustAllCerts, new java.security.SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		    // Create all-trusting host name verifier
		    HostnameVerifier allHostsValid = new HostnameVerifier() {
				
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return false;
				}
			};

		    // Install the all-trusting host verifier
		    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		    
		    System.out.println("***Begin Get BRIVA***");
			
		    BrivaData data = new BrivaData();
		    data.setAmount("45000");
		    data.setBrivaNo("77777");
		    data.setCustCode("1255");
		    data.setExpiredDate("2023-05-10 09:57:26");
		    data.setInstitutionCode("J104408");
		    data.setKeterangan("Retribusi April");
		    data.setNama("Mesa");
		    
		    Client client = Client.create();
			client.setConnectTimeout(60 * 1000);
			client.setReadTimeout(60 * 1000);
			
			String auth = "Bearer 2XKK4UF5rMb2PSSGUJcUXxOIrr4j";
			String xtimestamp = "2023-04-18T09:22:20.218Z";
			String jsonReq = mapper.writeValueAsString(data);
			
			StringBuffer payload = new StringBuffer();
			payload.append("path=/v1/briva/j104408/77777/1");
			payload.append("&");
			payload.append("verb=GET");
			payload.append("&");
			payload.append("token=" + auth);
			payload.append("&");
			payload.append("timestamp=" + xtimestamp);
			payload.append("&");
			payload.append("body=");
			
			System.out.println(payload.toString());
			String signature = encode(client_secret, payload.toString());
			signature = "t3tNJEY88bBfYg58Wg5rP0LCTzJaPY5HOnqZgdION/Y=";
			System.out.println(signature);
			
			WebResource webResource = client.resource(url_briva + "/j104408/77777/1");
			ClientResponse response = webResource.header("Authorization", auth)
					.header("BRI-Timestamp", xtimestamp)
					.header("BRI-Signature", signature)
					//.type(MediaType.APPLICATION_JSON)
					.get(ClientResponse.class);

			output = response.getEntity(String.class);
			System.out.println(output);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			//BrivaCreateResp obj = mapper.readValue(output, BrivaCreateResp.class);
			client.destroy();
			System.out.println("***End Get BRIVA***");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String encode(String key, String data) throws Exception {
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
		sha256_HMAC.init(secret_key);
		return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
	}
}

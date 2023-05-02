package com.sds.hakli.extension;

import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
			//api.createBriva();
			api.getBriva();
			//api.getStatusBriva();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BriApiToken getToken() throws Exception {
		BriApiToken obj = null;
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
			obj = mapper.readValue(output, BriApiToken.class);
			client.destroy();
			System.out.println(obj.getAccess_token());
			System.out.println("***End Access Token***");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public BrivaCreateResp createBriva(String token, BrivaData data) throws Exception {
		BrivaCreateResp obj = null;
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
			
//		    BrivaData data = new BrivaData();
//		    data.setAmount("100000");
//		    data.setBrivaNo("77777");
//		    data.setCustCode("6456454547");
//		    data.setExpiredDate("2023-05-10 09:57:26");
//		    data.setInstitutionCode("J104408");
//		    data.setKeterangan("Retribusi oktober");
//		    data.setNama("Ibrahim");
		    
		    Client client = Client.create();
			client.setConnectTimeout(60 * 1000);
			client.setReadTimeout(60 * 1000);
			
			String auth = "Bearer " + token;
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			String xtimestamp = dateFormatter.format(new Date());
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
					.accept(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class, jsonReq);

			output = response.getEntity(String.class);
			System.out.println(output);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			obj = mapper.readValue(output, BrivaCreateResp.class);
			client.destroy();
			System.out.println("***End Create BRIVA***");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
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
			
		    Client client = Client.create();
			client.setConnectTimeout(60 * 1000);
			client.setReadTimeout(60 * 1000);
			
			String auth = "Bearer 6j0k44tKwLKD1fkkcgdAikHLR1gM";
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			String xtimestamp = dateFormatter.format(new Date());
			
			StringBuffer payload = new StringBuffer();
			payload.append("path=/v1/briva/j104408/77777/6456454547");
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
			System.out.println(signature);
			
			WebResource webResource = client.resource(url_briva + "/j104408/77777/6456454547");
			ClientResponse response = webResource.header("Authorization", auth)
					.header("BRI-Timestamp", xtimestamp)
					.header("BRI-Signature", signature)
					//.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON)
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
	
	public void getStatusBriva() throws Exception {
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
		    
		    System.out.println("***Begin Get Status BRIVA***");
			
		    Client client = Client.create();
			client.setConnectTimeout(60 * 1000);
			client.setReadTimeout(60 * 1000);
			
			String auth = "Bearer 6j0k44tKwLKD1fkkcgdAikHLR1gM";
			
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			String xtimestamp = dateFormatter.format(new Date());
			System.out.println(xtimestamp);
			StringBuffer payload = new StringBuffer();
			payload.append("path=/v1/briva/status/j104408/77777/1");
			payload.append("&");
			payload.append("verb=GET");
			payload.append("&");
			payload.append("token=" + auth);
			payload.append("&");
			payload.append("timestamp=" + xtimestamp);
			payload.append("&");
			payload.append("body=");
			
			System.out.println("payload : " + payload.toString());
			String signature = encode(client_secret, payload.toString());
			System.out.println("signature : " + signature);
			System.out.println("response : ");
			WebResource webResource = client.resource(url_briva + "/status/j104408/77777/1");
			ClientResponse response = webResource.header("Authorization", auth)
					.header("BRI-Timestamp", xtimestamp)
					.header("BRI-Signature", signature)
					//.type(MediaType.APPLICATION_JSON_TYPE)
					.accept(MediaType.APPLICATION_JSON)
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

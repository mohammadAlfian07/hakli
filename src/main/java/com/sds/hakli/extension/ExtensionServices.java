package com.sds.hakli.extension;

import java.io.File;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.internal.MultiPartWriter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sds.hakli.domain.DocUpload;
import com.sds.hakli.domain.GenericResp;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ExtensionServices {
	
	Client client;
	
	public ExtensionServices() {
		client = Client.create();
		client.setConnectTimeout(40 * 1000);
		client.setReadTimeout(40 * 1000);
	}
	
	public static GenericResp getResource(String url, String json, String method, String token) {
		System.out.println("url :" + url + "\n" + "method :" + method + "\n" + "req :" + json);
		GenericResp rsp = null;
		try {
			Client client = Client.create();
			client.setConnectTimeout(40 * 1000);
			client.setReadTimeout(40 * 1000);

			WebResource webResource = client.resource(url.trim());
			WebResource.Builder builder = webResource.accept(MediaType.APPLICATION_JSON);
			builder.type(MediaType.APPLICATION_JSON);
			if (token != null)
				builder.header("Authorization", "Bearer " + token);
			
			ClientResponse response = null;
			if (method.equals("get")) {
				response = builder.get(ClientResponse.class);
			} else if (method.equals("post")) {
				response = builder.post(ClientResponse.class, json);
			} else if (method.equals("put")) {
				response = builder.put(ClientResponse.class, json);
			} else if (method.equals("delete")) {
				response = builder.delete(ClientResponse.class, json);
			}
			
			String output = response.getEntity(String.class);
			System.out.println("resp :" + output);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			rsp = mapper.readValue(output, GenericResp.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsp;
	}
	
	public static GenericResp getMedia(String url, DocUpload doc) {
		GenericResp rsp = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			DefaultClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getClasses().add(MultiPartWriter.class);
			Client client = Client.create(clientConfig);
			final WebResource resource = client.resource(url.trim());

			FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
			formDataMultiPart.field("file", new File(doc.getFile()),
					MediaType.APPLICATION_OCTET_STREAM_TYPE);
			formDataMultiPart.field("filename", doc.getFilename());

			formDataMultiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
			ClientResponse response = null;
			response = resource.type("multipart/form-data").post(ClientResponse.class, formDataMultiPart);
			String output = response.getEntity(String.class);
			System.out.println("response : " + output);
			client.destroy();
			rsp = mapper.readValue(output, GenericResp.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsp;
	}
}

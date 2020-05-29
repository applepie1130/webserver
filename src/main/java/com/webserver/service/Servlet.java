package com.webserver.service;

import java.net.URLConnection;
import java.util.Map;

import org.springframework.http.HttpMethod;

import com.webserver.model.Request;
import com.webserver.model.Response;

public class Servlet {
	
	public void service(Request request, Response response) {
		
		Map<String, String> headers = request.getHeaders();
		HttpMethod httpMethod = request.getHttpMethod();
		String httpPath = request.getHttpPath();
		String fileName = request.getFileName();
		
		switch (httpMethod) {
		case GET:
			// response 
			String contentType = URLConnection.getFileNameMap().getContentTypeFor(fileName);
			
			break;
			
		case HEAD:
		case POST: 
		case PUT:
		case PATCH:
		case DELETE:
		case OPTIONS:
		case TRACE:
			// HTTP Error 501: Not Implemented
			
			break;
			
		default:
			break;
		}
	}
}




package com.webserver.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;

import com.webserver.model.type.HtmlFileType;

import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
@Builder
public class Request {
	
    private InputStream inputStream;
    private BufferedReader bufferedReader;
    private HttpMethod httpMethod;
    private String httpPath;
    private String fileName;
    private String httpVersion;
    private Map<String, String> headers;

    public void parse() {
        try {
        	StringBuffer requestLine = new StringBuffer();
            while (true) {
            	int c = inputStream.read();
                if (c == '\r' || c == '\n') {
                	break;
                }
                requestLine.append((char) c);
            }
            
            String line = requestLine.toString();
            List<String> list = Arrays.stream(line.split("\\s+")).collect(Collectors.toList());

            if ( list != null && list.size() > 0 ) {
            	this.httpMethod = HttpMethod.valueOf(list.get(0));
            	this.httpPath = list.get(1);
            	
    			if ( StringUtils.endsWith(httpPath, "/") ) {
    				this.fileName += HtmlFileType.INDEX.getName();
    			}
            } 
            
            if ( list != null && list.size() > 2 ) {
            	this.httpVersion = list.get(2);
            }
            
            // Header Setting
            String headerLine = null;
            String key = null;
            String value = null;
            this.headers = new HashMap<>();
            
            int i = 0;
			while ((headerLine = bufferedReader.readLine()) != null) {
				i++;
				if (i == 1) {
					continue;
				}
				if ("".equals(headerLine)) {
					break;
				}
				int idx = headerLine.indexOf(':');
				
				if (idx != -1) {
					key = headerLine.substring(0, idx);
					value = headerLine.substring(idx + 1).trim();
					this.headers.put(key, value);
				}
            }
			
        } catch(Exception ex) {
            throw new RuntimeException();
        }
    }
}



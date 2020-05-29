package com.webserver.service;

import java.io.IOException;
import java.net.ServerSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class WebServer {
	
	@Value("${webserver.port}")
	private Integer port;
	
	private WebServerProcessor webServerProcessor;
	
	@Autowired
	WebServer(WebServerProcessor webServerProcessor) {
		this.webServerProcessor = webServerProcessor;
	}
	
	public void start() {
		try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
            	try {
                	webServerProcessor.run(server.accept());
                } catch (IOException ex) {
                    log.error("Error accepting connection", ex);
                }
            }
            
        } catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}
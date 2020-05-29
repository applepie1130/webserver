package com.webserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.webserver.service.WebServer;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = BasePackageLocation.class)
public class WebserverApplication implements CommandLineRunner {

	private final WebServer webServer;
	
	@Autowired
	WebserverApplication(WebServer webServer) {
		this.webServer = webServer;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WebserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		webServer.start();
	}
}

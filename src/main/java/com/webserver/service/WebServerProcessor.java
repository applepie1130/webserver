package com.webserver.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.webserver.model.Request;
import com.webserver.model.Response;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class WebServerProcessor {
	
	@Async("webserver-executor")
	public void run(Socket socket) throws IOException {
		InputStream inputStream = socket.getInputStream();
		OutputStream outputStream = socket.getOutputStream();
		
        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));
        
        // 헤더값 및 요청객체 생성 
        Request request = Request.builder()
						        	.inputStream(inputStream)
						        	.bufferedReader(bufferedReader)
						        	.build();
        request.parse();
        
        // 응답객체 생성
        Response response = Response.builder()
        							.outputStream(outputStream)
        							.printWriter(printWriter)
        							.build();

        String httpPath = request.getHttpPath();
        
	}
}
package com.webserver.model;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
	
	private OutputStream outputStream;
    private PrintWriter printWriter;
    private Long contentLength;
    private MediaType contentType;
    private HttpStatus httpStatus;
    private String message;
    
}

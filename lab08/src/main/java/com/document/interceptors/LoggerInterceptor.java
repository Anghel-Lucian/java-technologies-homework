package com.document.interceptors;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class LoggerInterceptor {
	
	private final String FILE_DIR = "lab07/resources/";
	private final String FILE_NAME = "lab07/resources/log.txt";
	
	@AroundInvoke
	public Object interceptCreate(InvocationContext ctx) throws Exception {
		System.out.print("[STARTING INTERCEPTION BY]: LoggerInterceptor");
		Object[] params = ctx.getParameters();
		String name = (String) params[0];
		String authors = (String) params[1];
		
		this.logDocumentToFile(name, authors);
		
		System.out.print("[INTERCEPTED BY]: LoggerInterceptor");
		
		return ctx.proceed();
	}
	
	private void logDocumentToFile(String name, String authors) throws IOException {
		String content = "[DOCUMENT]: " + name + "; [AUTHORS]: " + authors + "\n";
		File dir = new File(FILE_DIR);
		File file = new File(FILE_NAME);
		
		if(dir.mkdirs()) {
			file.createNewFile();
		}
		
		Files.write(Paths.get(FILE_NAME), content.getBytes(), StandardOpenOption.APPEND);
	}
	
}

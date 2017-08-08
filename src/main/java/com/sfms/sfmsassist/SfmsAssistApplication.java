package com.sfms.sfmsassist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath:META-INF/jasper.xml"})
@SpringBootApplication
public class SfmsAssistApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfmsAssistApplication.class, args);
	}
}

package com.gopolangmathole.employeedirectory;

import java.io.File;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

import com.gopolangmathole.employeedirectory.controller.EmployeeController;

@SpringBootApplication
@EnableJSONDoc
@EnableAspectJAutoProxy(proxyTargetClass = true)

public class EmployeeDirectory {

	public static void main(String[] args) {
		// make directory if it doesn't exist
		new File(EmployeeController.uploadDirectory).mkdir();

		SpringApplication.run(EmployeeDirectory.class, args);

	}
	
	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}

}


package com.gopolangmathole.employeedirectory;

import java.io.File;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.gopolangmathole.employeedirectory.controller.EmployeeController;


@SpringBootApplication
@EnableJSONDoc
@EnableAspectJAutoProxy(proxyTargetClass = true)

public class EmployeeDirectory {

	public static void main(String[] args) {
			new File(EmployeeController.uploadDirectory).mkdir();
			//starting spring application after scanning all packages.
			SpringApplication.run(EmployeeDirectory.class, args);

	}

}




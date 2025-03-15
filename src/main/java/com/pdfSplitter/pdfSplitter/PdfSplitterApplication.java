package com.pdfSplitter.pdfSplitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PdfSplitterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfSplitterApplication.class, args);
	}

}

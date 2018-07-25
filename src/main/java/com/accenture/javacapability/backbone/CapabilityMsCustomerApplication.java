package com.accenture.javacapability.backbone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages={"com.accenture.javacapability.backbone"})
@EnableEurekaClient
public class CapabilityMsCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapabilityMsCustomerApplication.class, args);
	}
}

package com.pablodev9.novainvest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class NovaInvestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NovaInvestApplication.class, args);
	}

}

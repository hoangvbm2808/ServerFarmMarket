package com.ltdd14.FarmMarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages = "com.ltdd14.FarmMarket")
@Configuration
public class FarmMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmMarketApplication.class, args);
	}

}

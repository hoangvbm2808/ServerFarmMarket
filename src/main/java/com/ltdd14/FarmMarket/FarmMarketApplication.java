package com.ltdd14.FarmMarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ltdd14.FarmMarket")
@EnableAutoConfiguration
public class FarmMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmMarketApplication.class, args);
	}

}

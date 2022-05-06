package com.niceweatherrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.niceweatherjpa.entities")
public class NiceWeatherRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NiceWeatherRestApplication.class, args);
	}

}

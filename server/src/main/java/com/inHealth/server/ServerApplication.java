package com.inHealth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;


@EnableKafka
@SpringBootApplication
public class ServerApplication  {


	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);

	}

}

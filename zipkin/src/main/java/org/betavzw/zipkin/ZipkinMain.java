package org.betavzw.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;





@SpringBootApplication
@EnableEurekaClient
public class ZipkinMain {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinMain.class, args);

	}

}

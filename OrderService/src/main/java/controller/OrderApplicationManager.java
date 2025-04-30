package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = { "controller", "service", "repo", "model" })
public class OrderApplicationManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(OrderApplicationManager.class, args);
	}

}

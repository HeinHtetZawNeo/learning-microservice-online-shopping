package com.learning.ordersvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

@SpringBootApplication
public class OrdersvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersvcApplication.class, args);
	}

}

package com.learning.inventorysvc;

import com.learning.inventorysvc.domain.Inventory;
import com.learning.inventorysvc.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventorysvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorysvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			inventoryRepository.save(Inventory.builder()
					.skuCode("iphone-13")
					.quantity(100)
					.build());

			inventoryRepository.save(Inventory.builder()
					.skuCode("iphone_13_red")
					.quantity(0)
					.build());
		};
	}
}

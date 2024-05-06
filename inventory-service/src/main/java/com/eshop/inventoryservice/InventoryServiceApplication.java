package com.eshop.inventoryservice;

import com.eshop.inventoryservice.Model.Inventory;
import com.eshop.inventoryservice.Repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
 

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(200);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("iphone_12");
			inventory2.setQuantity(200);

			Inventory inventory3 = new Inventory();
			inventory3.setSkuCode("iphone_13");
			inventory3.setQuantity(200);

			Inventory inventory4 = new Inventory();
			inventory4.setSkuCode("iphone_13");
			inventory4.setQuantity(200);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory2);
			inventoryRepository.save(inventory3);
			inventoryRepository.save(inventory4);

		};
	}

}

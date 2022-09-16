package com.Greg.BankApp;

import com.Greg.BankApp.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BankAppApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	void customerAttributsShouldNotBeNullAfterCreation(){

		//given
		Customer newCustomer = new Customer("123","Greg","Furgala","Anny-Szwed",
				"Krakow","30-389","Greg@wp.pl","606917576","logintest","haslo");
		//then
		assertNotNull(newCustomer.getIdnumber());
		assertNotNull(newCustomer.getFirstname());
		assertNotNull(newCustomer.getLastname());
		assertNotNull(newCustomer.getStreet());
		assertNotNull(newCustomer.getCity());
		assertNotNull(newCustomer.getZipcode());
		assertNotNull(newCustomer.getEmail());
		assertNotNull(newCustomer.getTelephone());
		assertNotNull(newCustomer.getLogin());
		assertNotNull(newCustomer.getPassword());
	}




}

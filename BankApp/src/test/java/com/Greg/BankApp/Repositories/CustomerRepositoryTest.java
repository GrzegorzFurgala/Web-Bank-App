package com.Greg.BankApp.Repositories;

import com.Greg.BankApp.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CustomerRepositoryTest {


    CustomerRepository repo =new CustomerRepository();

    /*
    @Test
    void fildsOfObjectFromTableCustomerSholdBeEqualsToObjectCreatedManualy() {
        //given
        Customer c1 = new Customer("Gr123","Grzegorz","Furgala","Anny Szwed","Krakow","30389","greg@wp.pl","606917576");
        Customer c2 = repo.readCustomerById("Gr123");

        //then
        assertEquals(c2,c1);
    }
    */




    @Test
    public void customerObjectShouldNotBeNullAfterEnterCorrectCredential(){
        //given
        String login = "Edi84";
        String haslo = "123";
        //when
        Customer cus = repo.logIn(login, haslo);
        //then
        assertNotNull(cus);

    }

}
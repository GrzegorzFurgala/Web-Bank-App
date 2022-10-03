package com.Greg.BankApp.Services;
import com.Greg.BankApp.Repositories.CustomerRepository;
import com.Greg.BankApp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepo;

    public CustomerService() {
    }

    public Customer createCustomer(Customer customer){
        customerRepo.createCustomer(customer);
        return customer;
    }

    public Customer saveCustomer(Customer customer){
        Customer cus = customerRepo.updateCustomer(customer);
        return cus;
    }

    public Customer readCustomerById(String idNumber){
        return customerRepo.readCustomerById(idNumber);
    }

    public Customer logIn(String login, String password){
            return customerRepo.logIn(login,password);
    }

}

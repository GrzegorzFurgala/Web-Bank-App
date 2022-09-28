package com.Greg.BankApp.Services;
import com.Greg.BankApp.Repositories.CustomerRepository;
import com.Greg.BankApp.domain.BankEmployee;
import com.Greg.BankApp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Scanner;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    AccountService accService;
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

    public List<Customer> readAllCustomers(){
        return customerRepo.readAllCustomers();
    }

    public void deleteCustomerById(String idnumber){
        customerRepo.deleteCustomerById(idnumber);
    }

    public Customer logIn(String login, String password){
            return customerRepo.logIn(login,password);
    }

}

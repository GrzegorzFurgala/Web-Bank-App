package com.Greg.BankApp.Services;
import com.Greg.BankApp.Repositories.CustomerRepository;
import com.Greg.BankApp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Scanner;

@Service
public class CustomerService {
    Scanner sc = new Scanner(System.in);
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    AccountService accService;
    public CustomerService() {
    }

    public Customer createCustomer(Customer customer){
        /*
        System.out.println("Enter Your first name");
        String firstName = sc.nextLine();
        System.out.println("Enter Your last name");
        String lastName = sc.nextLine();
        System.out.println("Enter Your ID-number");
        String idnumber = sc.nextLine();
        System.out.println("Enter street details");
        String street = sc.nextLine();
        System.out.println("Enter City");
        String city = sc.nextLine();
        System.out.println("Enter zip-code");
        String zipCode = sc.nextLine();
        System.out.println("Enter e-mail");
        String email = sc.nextLine();
        System.out.println("Enter thelephone");
        String telephone = sc.nextLine();
        System.out.println("Set-up Your login");
        String login = sc.nextLine();
        System.out.println("Set-up Your password");
        String password = sc.nextLine();
        */
       //Customer customer = new Customer(idnumber,firstName,lastName,street,city,zipCode,email,telephone,login,password);
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

    public void changeFirstName(Customer customer){
        String newFirstName;
        System.out.println("Enter new first name");
        newFirstName = sc.nextLine();
        customer.setFirstname(newFirstName);
        customerRepo.updateCustomer(customer);
    }

    public void changeLastName(Customer customer) {
        String newLastName;
        System.out.println("Enter new last name");
        newLastName = sc.nextLine();
        customer.setLastname(newLastName);
        customerRepo.updateCustomer(customer);
    }

    public void changeCity(Customer customer) {
        String newCity;
        System.out.println("Enter new city");
        newCity = sc.nextLine();
        customer.setCity(newCity);
        customerRepo.updateCustomer(customer);
    }
    public void changeStreet(Customer customer) {
        String newStreet;
        System.out.println("Enter new street");
        newStreet = sc.nextLine();
        customer.setStreet(newStreet);
        customerRepo.updateCustomer(customer);
    }
    public void changeZipcode(Customer customer) {
        String newZipCode;
        System.out.println("Enter new zipcode");
        newZipCode = sc.nextLine();
        customer.setZipcode(newZipCode);
        customerRepo.updateCustomer(customer);
    }
    public void changeTelephone(Customer customer) {
        String newPhone;
        System.out.println("Enter new phone number");
        newPhone = sc.nextLine();
        customer.setTelephone(newPhone);
        customerRepo.updateCustomer(customer);
    }
    public void changeEmail(Customer customer) {
        String newEmail;
        System.out.println("Enter new e-mail number");
        newEmail = sc.nextLine();
        customer.setEmail(newEmail);
        customerRepo.updateCustomer(customer);
    }
}

package com.Greg.BankApp.Services;

import com.Greg.BankApp.Repositories.EmployeeRepository;
import com.Greg.BankApp.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    Scanner sc = new Scanner(System.in);

    public BankEmployee createBankEmployee(){
        String firstname;
        String lastname;
        String login;
        String password;
        System.out.println("Enter your first name");
        firstname = sc.nextLine();
        System.out.println("Enter your last name");
        lastname = sc.nextLine();
        System.out.println("set-up login");
        login = sc.nextLine();
        System.out.println("set-up password");
        password = sc.nextLine();
        BankEmployee bankEmployee = new BankEmployee(firstname,lastname,login,password);
        employeeRepository.persist(bankEmployee);
        return bankEmployee;
    }

    public BankEmployee saveEmployee(BankEmployee bankEmployee){
        BankEmployee emp = employeeRepository.updateEmployee(bankEmployee);
        return emp;
    }

    public BankAdmin createBankAdmin(){
        String firstname;
        String lastname;
        String login;
        String password;
        System.out.println("Enter your first name");
        firstname = sc.nextLine();
        System.out.println("Enter your last name");
        lastname = sc.nextLine();
        System.out.println("set-up login");
        login = sc.nextLine();
        System.out.println("set-up password");
        password = sc.nextLine();
        BankAdmin bankAdmin = new BankAdmin(firstname,lastname,login,password);
        employeeRepository.persist(bankAdmin);
        return bankAdmin;
    }

    public BankAdmin saveAdmin(BankAdmin bankAdmin){
        BankAdmin emp = employeeRepository.updateEmployee(bankAdmin);
        return emp;
    }

    public void deleteEmployeeById(int idEmployee){
        employeeRepository.deleteEmployeeById(idEmployee);
    }

    public void deleteAdminById(int idAdmin){
        employeeRepository.deleteAdminById(idAdmin);
    }




    public void readAllCustomers() {
        List<Customer>customerList = employeeRepository.readAllCustomers();
        customerList.stream().forEach(customer -> System.out.println(customer));
    }
    public void readCustomerById(String idnumber){
        Customer customer = employeeRepository.readCustomerById(idnumber);
        System.out.println(customer);
    }
    public void readAllAccounts(){
        List<Account> accountList = employeeRepository.readAllAccounts();
        accountList.forEach(account -> System.out.println(account));
    }
    public void readAccountByAccountNumber(int accountNumber){
        Account account = employeeRepository.readAccountByAccountNumber(accountNumber);
        System.out.println(account);
    }


    public BankEmployee logInEmployee(String login, String password) {
        BankEmployee employee = employeeRepository.logInEmployee(login,password);
        return employee;
    }

    public BankAdmin logInAdmin(String login, String password) {
        BankAdmin admin = employeeRepository.logInAdmin(login,password);
        return admin;
    }

    public void approveCustomerBankAccount(int accountNumber){
        employeeRepository.approveCustomerAccount(accountNumber);
    }
    public void disapproveCustomerBankAccount(int accountNumber){
        employeeRepository.disapproveCustomerAccount(accountNumber);
    }


}

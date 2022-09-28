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
    @Autowired
    AccountService accountService;
    Scanner sc = new Scanner(System.in);

    public BankEmployee createBankEmployee(){
        // TODO: 28/09/2022
        return null;
    }

    public BankEmployee saveEmployee(BankEmployee bankEmployee){
        BankEmployee emp = employeeRepository.updateEmployee(bankEmployee);
        return emp;
    }
    public BankAdmin createBankAdmin(){
        // TODO: 28/09/2022
        return null;
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

    //----------------------Operations--on--accounts-------------------------------//

    public List<Customer> readAllCustomers() {
        List<Customer> customerList = employeeRepository.readAllCustomers();
        return customerList;
    }
    public Customer readCustomerById(String idnumber){
        Customer customer = employeeRepository.readCustomerById(idnumber);
        return customer;
    }
    public List<Account> readAllAccounts(){
        List<Account> accountList = employeeRepository.readAllAccounts();
        return accountList;
    }
    public Account readAccountByAccountNumber(int accountNumber){
        Account account = employeeRepository.readAccountByAccountNumber(accountNumber);
        System.out.println(account);
        return account;
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
    public List<Account> showNotApprovedAccounts(){
        List<Account> notApprovedAccountst= employeeRepository.showNotApprovedAccounts();
        return notApprovedAccountst;
    }
    public void depositByAdmin(int accountNumber, double amount){
        Account account = readAccountByAccountNumber(accountNumber);
        double balance = account.getAccount_balance();
        double newBalance;
        newBalance = balance + amount;
        account.setAccount_balance(newBalance);
        accountService.saveAccount(account);
    }
    public void withdrawByAdmin(Account account, double newBalance){
        account.setAccount_balance(newBalance);
        accountService.saveAccount(account);

    }
    public void transferByAdmin(Account withdrawAccount, double amount, int depositAccountNumber){

            double withdrawBalance = withdrawAccount.getAccount_balance();
            Account depositAccount = readAccountByAccountNumber(depositAccountNumber);

            double depositAccountBalance = depositAccount.getAccount_balance();
            double updatedDepositAccountBalance = depositAccountBalance + amount;
            depositAccount.setAccount_balance(updatedDepositAccountBalance);
            accountService.saveAccount(depositAccount);

            double updatedWitdrawBalance = withdrawBalance - amount;
            withdrawAccount.setAccount_balance(updatedWitdrawBalance);
            accountService.saveAccount(withdrawAccount);

    }

}

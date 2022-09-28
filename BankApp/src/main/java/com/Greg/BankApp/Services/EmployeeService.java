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


    //----------------------Operations--on--accounts-------------------------------//

    public List<Customer> readAllCustomers() {
        List<Customer> customerList = employeeRepository.readAllCustomers();
        //WEB customerList.stream().forEach(customer -> System.out.println(customer));
        return customerList;
    }
    public Customer readCustomerById(String idnumber){
        Customer customer = employeeRepository.readCustomerById(idnumber);
        //WEB System.out.println(customer);
        return customer;
    }
    public List<Account> readAllAccounts(){
        List<Account> accountList = employeeRepository.readAllAccounts();
        //WEB accountList.forEach(account -> System.out.println(account));
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
        //WEB notApprovedAccountst.forEach(account -> System.out.println(account));
    }
    public void depositByAdmin(int accountNumber, double amount){
        Account account = readAccountByAccountNumber(accountNumber);
        double balance = account.getAccount_balance();
        double newBalance;
        //WEB System.out.println("Enter amount to deposit");
        //WEB double amount = sc.nextDouble();
        newBalance = balance + amount;
        account.setAccount_balance(newBalance);
        accountService.saveAccount(account);
    }
    public void withdrawByAdmin(Account account, double newBalance){
       /*
        Account account = readAccountByAccountNumber(accountNumber);
        double balance = account.getAccount_balance();
        double newBalance;
        //WEB System.out.println("Enter amount to withdraw");
        //WEB double amount = sc.nextDouble();

        if(balance < amount){
            System.out.println("You dont have enogh mony");
            System.out.println("Your current balnace is: "+ account.getAccount_balance());
        }else{
            newBalance = balance - amount;
            account.setAccount_balance(newBalance);
            accountService.saveAccount(account);
        }
        */
        account.setAccount_balance(newBalance);
        accountService.saveAccount(account);


    }
    public void transferByAdmin(Account withdrawAccount, double amount, int depositAccountNumber){



        //Account withdrawAccount = readAccountByAccountNumber(accountNumber);
        double withdrawBalance = withdrawAccount.getAccount_balance();
        //System.out.println("Enter amount you want to withdraw");
        //double withdrawAmount = sc.nextDouble();
        /*
        if(withdrawBalance<withdrawAmount){
            System.out.println("You dont have enogh mony");
            System.out.println("Your current balnace is: "+ withdrawBalance);
        }else {
        */
            //System.out.println("Enter account number for deposit");
            //int depositAccountNumber = sc.nextInt();
            Account depositAccount = readAccountByAccountNumber(depositAccountNumber);

            double depositAccountBalance = depositAccount.getAccount_balance();
            double updatedDepositAccountBalance = depositAccountBalance + amount;
            depositAccount.setAccount_balance(updatedDepositAccountBalance);
            accountService.saveAccount(depositAccount);

            double updatedWitdrawBalance = withdrawBalance - amount;
            withdrawAccount.setAccount_balance(updatedWitdrawBalance);
            accountService.saveAccount(withdrawAccount);
        //}

    }





}

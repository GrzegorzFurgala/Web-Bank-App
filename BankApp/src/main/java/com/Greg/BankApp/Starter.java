package com.Greg.BankApp;
import com.Greg.BankApp.Repositories.AccountRepository;
import com.Greg.BankApp.Repositories.EmployeeRepository;
import com.Greg.BankApp.Services.AccountService;
import com.Greg.BankApp.Services.CustomerService;
import com.Greg.BankApp.Services.EmployeeService;
import com.Greg.BankApp.domain.Account;
import com.Greg.BankApp.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Starter implements CommandLineRunner {

    @Autowired
    CustomerService service;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accRepo;
    @Autowired
    Menu menu;
    @Autowired
    EmployeeRepository er;
    @Autowired
    EmployeeService employeeService;

    @Override
    public void run(String... args) throws Exception {
        /*
        Customer cus = service.createCustomer();
       Account acc = accountService.createNewAccount();
       Account acc2 = accountService.createNewAccount();
        cus.addAccountToTheList(acc);
        cus.addAccountToTheList(acc2);
        service.saveCustomer(cus);
        */



        //System.out.println(accRepo.readAllAcceptedAccountsByOwnerId("GR123"));

        menu.mainMenu();

        //employeeService.approveCustomerBankAccount(56);


    }
}

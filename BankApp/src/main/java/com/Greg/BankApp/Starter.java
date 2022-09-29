package com.Greg.BankApp;
import com.Greg.BankApp.Repositories.AccountRepository;
import com.Greg.BankApp.Repositories.EmployeeRepository;
import com.Greg.BankApp.Services.AccountService;
import com.Greg.BankApp.Services.CustomerService;
import com.Greg.BankApp.Services.EmployeeService;
import com.Greg.BankApp.domain.Account;
import com.Greg.BankApp.domain.BankEmployee;
import com.Greg.BankApp.menu.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


        Logger logger = LoggerFactory.getLogger(Starter.class);
        menu.mainMenu();



    }
}

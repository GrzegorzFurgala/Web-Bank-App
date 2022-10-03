package com.Greg.BankApp.menu;
import com.Greg.BankApp.Services.AccountService;
import com.Greg.BankApp.Services.CustomerService;
import com.Greg.BankApp.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Menu {
        @Autowired
        AccountService accService;
        @Autowired
        CustomerService cusService;
        @Autowired
        EmployeeService employeeService;


        public void mainMenu() {


        }
}
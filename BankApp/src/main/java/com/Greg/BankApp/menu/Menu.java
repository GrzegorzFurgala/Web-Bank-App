package com.Greg.BankApp.menu;
import com.Greg.BankApp.Repositories.AccountRepository;
import com.Greg.BankApp.Services.AccountService;
import com.Greg.BankApp.Services.CustomerService;
import com.Greg.BankApp.Services.EmployeeService;
import com.Greg.BankApp.domain.Account;
import com.Greg.BankApp.domain.BankAdmin;
import com.Greg.BankApp.domain.BankEmployee;
import com.Greg.BankApp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


@Component
public class Menu {
        @Autowired
        AccountService accService;
        @Autowired
        CustomerService cusService;
        @Autowired
        EmployeeService employeeService;

        Scanner sc = new Scanner(System.in);
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatedTime = time.format(format);

        public void mainMenu() {


        }
}
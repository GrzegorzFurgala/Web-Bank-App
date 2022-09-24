package com.Greg.BankApp.controllers;

import com.Greg.BankApp.Services.EmployeeService;
import com.Greg.BankApp.domain.BankEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankStaffControllers {

    @Autowired
    EmployeeService employeeService;


    @RequestMapping("/employeeCredentialsForm")
    public String employeeCredentialsForm(){

        return "employeeCredentialsForm";
    }

    @RequestMapping("/employeeAccountView")
    public String employeeAccountView(@RequestParam("login") String login,
                                      @RequestParam("password") String password){

        System.out.println(login);   // stworzyc logike logowania, wyciagnac obiekt Employee , jezeli jest pusty to bledny login lub haslo
        System.out.println(password);

        return "employeeAccountView"; //<-----stworzyc widok
    }

}

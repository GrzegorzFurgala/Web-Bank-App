package com.Greg.BankApp.controllers;

import com.Greg.BankApp.Repositories.CustomerRepository;
import com.Greg.BankApp.Services.CustomerService;
import com.Greg.BankApp.domain.Account;
import com.Greg.BankApp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("cusIntersession")
public class MenuControllers {

    @Autowired
    CustomerService customerService;

    @GetMapping("/mainMenu")
    public String mainMenu(){
        return "mainMenu";
    }

    @GetMapping("/credentials")
    public String credentials(){

        return "credentials";
    }


    @GetMapping("/customerAccountView")
    public String loginIntoCustomerAccount(
            @RequestParam(value = "login",required = false) String login,
            @RequestParam(value = "password",required = false) String pass,Model model){
            Customer customer = customerService.logIn(login,pass);
            model.addAttribute("cusIntersession", customer);

        return "customerAccountView";
    }







}

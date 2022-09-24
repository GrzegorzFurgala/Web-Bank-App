package com.Greg.BankApp.controllers;

import com.Greg.BankApp.Repositories.CustomerRepository;
import com.Greg.BankApp.Services.CustomerService;
import com.Greg.BankApp.domain.Account;
import com.Greg.BankApp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"cusIntersession","login","password"})
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
            @RequestParam(value = "login") String login,
            @RequestParam(value = "password") String pass,
            Model model){

            Customer customer = customerService.logIn(login,pass);
            if(customer == null){
                return "redirect:/wrongPass";
            }
            model.addAttribute("login",login);
            model.addAttribute("password",pass);
            model.addAttribute("cusIntersession", customer);
        return "customerAccountView";
    }
    @GetMapping("/wrongPass")
    public String wrongPass(){
        return "wrongPass";
    }

    @RequestMapping("/bankStaffMenu")
    public String bankStaffMenu(){

        return "bankStaffMenu";
    }





}

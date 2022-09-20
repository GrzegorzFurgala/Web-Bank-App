package com.Greg.BankApp.controllers;

import com.Greg.BankApp.Services.AccountService;
import com.Greg.BankApp.domain.Account;
import com.Greg.BankApp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("cusIntersession")
public class AccountsController {

    @Autowired
    AccountService accountService;

        @GetMapping("/accounts")
        public String getAllAccounts(Model model){
            List<Account> allAccounts = accountService.readAllAccount();
            model.addAttribute("accounts",allAccounts);
        return "allaccounts";
        }

        @GetMapping("/newAccount")
        public String newBankAccountForm(Model model){
            model.addAttribute("newAcc", new Account());
            return "newBankAccount";
        }

        @PostMapping("/newAccounts")
        public String saveAccount(Account account){
            accountService.saveAccount(account);
            return "redirect:/accounts";
        }

        @GetMapping("/bankAccountList")
        public String customerApprovedBankAccounts(Model model){
            //todo kontroler sluzy do wyswietlania wszystkich zaakceptowanych kont, potrzebuje id klienta zeby wykorzystac metode
           Customer edyta = (Customer)model.getAttribute("cusIntersession");
            System.out.println(edyta.getFirstname());
            System.out.println(edyta.getLastname());
            System.out.println(edyta);

            //accService.readAllAcceptedAccountsByOwnerId(idNumber)
            return "allaccounts";
        }



}

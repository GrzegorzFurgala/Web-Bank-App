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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.concurrent.Callable;

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
            Customer customer = (Customer)model.getAttribute("cusIntersession");
            List<Account>approvedBankAccounts = accountService.readAllAcceptedAccountsByOwnerId(customer.getIdnumber());
            model.addAttribute("approvedAccounts",approvedBankAccounts);
            return "customerApprovedBankAccountList";
        }

        @GetMapping("/bankAccountView")
        public String bankAccountView(@RequestParam("accnumb") Integer account_number, Model model){
                Account account = accountService.findAccountByAccNumber(account_number);
                model.addAttribute("acc",account);
            return "bankAccountView";
        }

    @GetMapping("/deposit")
    public String deposit(@RequestParam("accnumb") Integer account_number, Model model){
        System.out.println(account_number);
        Account account = accountService.findAccountByAccNumber(account_number);
            model.addAttribute("accnumb", account);
        return "deposit";
    }

    @GetMapping("/depositPost")
    public String depositPost(@RequestParam("kwota") Double kwota,
                            @RequestParam("accnumb") Integer account_number ) {

        Account account = accountService.findAccountByAccNumber(account_number);
        double balance = account.getAccount_balance();
        accountService.deposit(account_number,balance,kwota);
        System.out.println("dupa");
        return "redirect:/bankAccountList";
    }

}

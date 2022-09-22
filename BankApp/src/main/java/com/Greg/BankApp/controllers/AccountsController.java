package com.Greg.BankApp.controllers;
import com.Greg.BankApp.Services.AccountService;
import com.Greg.BankApp.domain.Account;
import com.Greg.BankApp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

@Controller
@SessionAttributes({"cusIntersession", "accountNumber","login","password"})
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
        public String bankAccountView(@RequestParam("accnumb") Integer account_number,
                                      Model model){

                model.addAttribute("accountNumber", account_number);
                Account account = accountService.findAccountByAccNumber(account_number);
                model.addAttribute("acc",account);
                String login = (String)model.getAttribute("login");
                String password = (String)model.getAttribute("password");
            return "bankAccountView";
        }
        //-------------------DEPOSIT---COTROLLERS--------------------------------------
    @RequestMapping("/deposit" )
    public String deposit(@RequestParam("accnumb") Integer account_number, Model model){
            Account account = accountService.findAccountByAccNumber(account_number);
            model.addAttribute("accnumb", account);
        return "deposit";
    }

    @GetMapping("/depositPost")
    public String depositPost(@RequestParam("kwota") Double kwota, Model model) {

            if(kwota <= 0){
                return "redirect:/depositNegativeValue";
            }
        int account_number = (int)model.getAttribute("accountNumber");
        Account account = accountService.findAccountByAccNumber(account_number);
        double balance = account.getAccount_balance();
        accountService.deposit(account_number,balance,kwota);
        return "redirect:/bankAccountList";
    }
    @GetMapping("/depositNegativeValue")
    public String depositWrongValue(Model model) {
            int accountNumber = (int)model.getAttribute("accountNumber");
        return "depositNegativeValue";
    }

    //-------------------WITHDRAW---CONTROLLERs------------------------------------------------
    @RequestMapping("/withdraw")
    public String withdraw(@RequestParam("accnumb") Integer account_number, Model model){
        Account account = accountService.findAccountByAccNumber(account_number);
        model.addAttribute("account", account);
        return "withdraw";
    }
    @GetMapping("/withdrawPost")
    public String withdrawPost(@RequestParam("kwota") Double kwota, Model model) {
        int account_number = (int)model.getAttribute("accountNumber");
        Account account = accountService.findAccountByAccNumber(account_number);
        double balance = account.getAccount_balance();

        if(kwota<=0){
            return "redirect:/withdrawNegativeValue";
        }else if(balance<kwota){
            return "redirect:/lackofFunds";
        }
        accountService.withdraw(account_number,balance,kwota);
        return "redirect:/bankAccountList";
    }
    @GetMapping("/lackofFunds")
    public String lackofFundsWithdraw(Model model) {
        int accountNumber = (int)model.getAttribute("accountNumber");
        return "lackofFunds";
    }
    @GetMapping("withdrawNegativeValue")
    public String withdrawNegativeValue(Model model){
        int accountNumber = (int)model.getAttribute("accountNumber");
        return "withdrawNegativeValue";
    }


    //-------------------Transfer---CONTROLLERS------------------------------------------------
    @RequestMapping("/transfer")
    public String transfer(@RequestParam("accnumb") Integer account_number, Model model){
        Account account = accountService.findAccountByAccNumber(account_number);
        model.addAttribute("account", account);
        return "transfer";
    }
    @GetMapping("/transferPost")
    public String transferPost(@RequestParam("kwota") Double kwota,
                               @RequestParam("receiver") Integer receiver,
                               Model model) {

        int account_number = (int)model.getAttribute("accountNumber");
        Account account = accountService.findAccountByAccNumber(account_number);
        double balance = account.getAccount_balance();

        if(kwota <= 0){
            return "redirect:/transferNegativeValue";
        }else if(balance < kwota){
            return "redirect:/transferLackofFunds";
        }
        accountService.transfer(account_number,balance,kwota,receiver);
        return "redirect:/bankAccountList";
    }
    @GetMapping("/transferNegativeValue")
    public String transferNegativeValue(Model model){
        int accountNumber = (int)model.getAttribute("accountNumber");
       return "transferNegativeValue";
    }
    @GetMapping("transferLackofFunds")
    public String transferLackofFunds(Model model){
        int accountNumber = (int)model.getAttribute("accountNumber");
         return "transferLackofFunds";
    }


}

package com.Greg.BankApp.controllers;
import com.Greg.BankApp.Services.AccountService;
import com.Greg.BankApp.Services.CustomerService;
import com.Greg.BankApp.domain.Account;
import com.Greg.BankApp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@SessionAttributes({"cusIntersession", "accountNumber","login","password"})
public class AccountsController {
    @Autowired
    AccountService accountService;
    @Autowired
    CustomerService customerService;

        @GetMapping("/accounts")
        public String getAllAccounts(Model model){
            List<Account> allAccounts = accountService.readAllAccount();
            model.addAttribute("accounts",allAccounts);
        return "allaccounts";
        }

        @PostMapping("/newAccounts")
        public String saveAccount(Account account, Model model){
            //accountService.createNewAccount(account);
            Customer customer = (Customer)model.getAttribute("cusIntersession");

            Account updatedAccount = accountService.saveAccount(account);
            updatedAccount.setOwner_id(customer.getIdnumber());
            System.out.println("dupa +"+updatedAccount);

            customer.addAccountToTheList(updatedAccount);//<------------!!!!!!!!!!!!!!!!!!!!!sprawdzic czy to nie powduje blad pierwszego konta
            customerService.saveCustomer(customer);
            System.out.println(customer);
            return "redirect/bankAccountList";
        }
        @GetMapping("/bankAccountList")
        public String customerApprovedBankAccounts(Model model){
            Customer customer = (Customer)model.getAttribute("cusIntersession");
            List<Account>approvedBankAccounts = accountService.readAllAcceptedAccountsByOwnerId(customer.getIdnumber());
            System.out.println(approvedBankAccounts);
            model.addAttribute("approvedAccounts",approvedBankAccounts);
            return "customerApprovedBankAccountList";
        }
        @RequestMapping("/bankAccountView")
        public String bankAccountView(@RequestParam("accnumb") Integer account_number,
                                      Model model){
                model.addAttribute("accountNumber", account_number);
                Account account = accountService.findAccountByAccNumber(account_number);
                model.addAttribute("acc",account);
            return "bankAccountView";
        }
        //-------------------DEPOSIT---COTROLLERS--------------------------------------
    @RequestMapping("/deposit" )
    public String deposit(@RequestParam("accnumb") Integer account_number,
                          Model model){
            Account account = accountService.findAccountByAccNumber(account_number);
            model.addAttribute("accnumb", account);
        return "deposit";
    }
    @GetMapping("/depositPost")
    public String depositPost(@RequestParam("kwota") double kwota,
                              Model model) {

        String operation = "deposit";
        model.addAttribute("operation",operation);

            if(kwota <= 0){
                return "errorMessagesCustomer";
            }
        int account_number = (int)model.getAttribute("accountNumber");
        Account account = accountService.findAccountByAccNumber(account_number);
        double balance = account.getAccount_balance();
        accountService.deposit(account_number,balance,kwota);
        return "succesfulOperation";
    }

    //-------------------WITHDRAW---CONTROLLERs------------------------------------------------
    @RequestMapping("/withdraw")
    public String withdraw(@RequestParam("accnumb") Integer account_number,
                           Model model){
        Account account = accountService.findAccountByAccNumber(account_number);
        model.addAttribute("account", account);
        return "withdraw";
    }
    @GetMapping("/withdrawPost")
    public String withdrawPost(@RequestParam("kwota") Double kwota,
                               Model model) {
        int account_number = (int)model.getAttribute("accountNumber");
        Account account = accountService.findAccountByAccNumber(account_number);
        double balance = account.getAccount_balance();
        model.addAttribute("kwota",kwota);
        model.addAttribute("balance",balance);

        String operation = "withdraw";
        model.addAttribute("operation",operation);

        if(kwota<=0){
            return "errorMessagesCustomer";
        }else if(balance<kwota){
            return "errorMessagesCustomer";
        }
        accountService.withdraw(account_number,balance,kwota);
        return "succesfulOperation";
    }

    //-------------------Transfer---CONTROLLERS------------------------------------------------
    @RequestMapping("/transfer")
    public String transfer(@RequestParam("accnumb") Integer account_number,
                           Model model){
        Account account = accountService.findAccountByAccNumber(account_number);
        model.addAttribute("account", account);
        return "transfer";
    }
    @GetMapping("/transferPost")
    public String transferPost(@RequestParam("kwota") double kwota,
                               @RequestParam("receiver") Integer receiver,
                               Model model) {

        int account_number = (int)model.getAttribute("accountNumber");
        Account account = accountService.findAccountByAccNumber(account_number);
        double balance = account.getAccount_balance();
        model.addAttribute("kwota",kwota);
        model.addAttribute("balance",balance);

        String operation = "transfer";
        model.addAttribute("operation",operation);


        if(kwota <= 0){
            return "errorMessagesCustomer";
        }else if(balance < kwota){
            return "errorMessagesCustomer";
        }
        accountService.transfer(account_number,balance,kwota,receiver);
        return "succesfulOperation";
    }

    //-------------------Applay--for--new--Account----------------------

    @GetMapping("/newAccount")
    public String newBankAccountForm(Model model){
        model.addAttribute("newAcc", new Account());
        return "newBankAccountForm";
    }

}

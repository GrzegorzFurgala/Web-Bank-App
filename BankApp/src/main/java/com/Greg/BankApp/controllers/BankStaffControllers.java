package com.Greg.BankApp.controllers;
import com.Greg.BankApp.Services.EmployeeService;
import com.Greg.BankApp.domain.Account;
import com.Greg.BankApp.domain.BankAdmin;
import com.Greg.BankApp.domain.BankEmployee;
import com.Greg.BankApp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"login","password","position","employee","flag"})
public class BankStaffControllers {
    @Autowired
    EmployeeService employeeService;


    @RequestMapping("/employeeCredentialsForm/{flag}")
    public String employeeCredentialsForm(@PathVariable("flag") String flag,
                                          Model model){
        model.addAttribute("flag",flag);
        return "employeeCredentialsForm";
    }

    @RequestMapping("/employeeAccountView")
    public String employeeAccountView(@RequestParam("login") String login,
                                      @RequestParam("password") String password,
                                      Model model){
        model.addAttribute("login",login);
        model.addAttribute("password",password);
        String position = (String)model.getAttribute("flag");


        BankEmployee employee = employeeService.logInEmployee(login, password, position);

        if (employee == null) {
            return "wrongPass";
        }

        model.addAttribute("employee", employee);
        position = employee.getPosition();
        model.addAttribute("position", position);

        return "staffAccountView";
    }

    @RequestMapping("/readAllCustomerAccount")
    public String allCustomers(Model model){
        List<Customer> allCustomerList = employeeService.readAllCustomers();
        model.addAttribute("allCustomerList",allCustomerList);
        return "readAllCustomerAccount";
    }

    @RequestMapping("/readIndividualCustomerForm")
    public String readIndividualCustomerAccount(){
        return"readIndividualCustomerForm";
    }

    @RequestMapping("/readIndividualCustomer")
    public String readIndividualCustomer(@RequestParam ("idNumber") String idnumber,
                                         Model model){

        Customer customer = employeeService.readCustomerById(idnumber);
        model.addAttribute("customer", customer);
        return "readIndividualCustomer";
    }

    @RequestMapping("/readAllBankAccounts")
    public String allBankAccounts(Model model) {
        List<Account> allBankAccountList = employeeService.readAllAccounts();
        model.addAttribute("allBankAccounts", allBankAccountList);
        return "readAllBankAccounts";
    }

    @RequestMapping("/readIndividualBankForm")
    public String readIndividualBankForm(){
        return "readIndividualBankForm";
    }
    @RequestMapping("/readIndividualBankAccount")
    public String readIndividualBankAccount(@RequestParam ("account_number") int accountNumber,
                                         Model model){
        Account account = employeeService.readAccountByAccountNumber(accountNumber);
        model.addAttribute("account",account);
        return "readIndividualBankAccount";
    }

    @RequestMapping("/approveAccountForm")
    public String approvedAccount(){
        return "approveAccountForm";
    }

    @RequestMapping("/approveAccount")
    public String approveAccount(@RequestParam("account_number") int accountNumber){
        employeeService.approveCustomerBankAccount(accountNumber);
        return "staffAccountView";
    }

    @RequestMapping("/notApprovedAccounts")
    public String notApprovedAccounts(Model model){
        List<Account> notApprovedAccounts = employeeService.showNotApprovedAccounts();
        model.addAttribute("notApprovedAccounts",notApprovedAccounts);
        return "notApprovedAccounts";
    }
    //---------------Bank--Admin------------------------------------------------------------
    //--------------------DEPOSIT--------------------------------------------------------------

    @RequestMapping("/depositByAdminForm")
    public String depositByAdminForm(){
        return "depositByAdminForm";
    }

    @RequestMapping("/depositByAdmin")
    public String depositByAdmin(@RequestParam("accountNumber") int accountNumber,
                                 @RequestParam("amount") double amount,
                                 Model model){

        String operation = "deposit";
        model.addAttribute("operation",operation);

        if(amount <= 0){
            return "errorMessagesAdmin";
        }
        employeeService.depositByAdmin(accountNumber,amount);
        return "staffAccountView";
    }

    //---------------------Withdraw--------------------------------------------------------------

    @RequestMapping("/withdrawByAdminForm")
    public String withdrawByAdminForm(){
        return "withdrawByAdminForm";
    }


    @RequestMapping("/withdrawByAdmin")
    public String withdrawByAdmin(@RequestParam("accountNumber") int accountNumber,
                                  @RequestParam("amount")  double amount,
                                  Model model){

        Account account = employeeService.readAccountByAccountNumber(accountNumber);
        double balance = account.getAccount_balance();
        double newBalance = balance - amount;
        model.addAttribute("amount",amount);
        model.addAttribute("balance",balance);
        String operation = "withdraw";
        model.addAttribute("operation",operation);

            if(amount <= 0){
                return "errorMessagesAdmin";
            }else if(newBalance < 0){
                return "errorMessagesAdmin";
            }
        employeeService.withdrawByAdmin(account, newBalance);
        return "staffAccountView";
    }

    //---------------------Transfer--------------------------------------------------------------

    @RequestMapping("/transferByAdminForm")
    public String transferbyAdminForm(){
        return "transferByAdminForm";
    }

    @RequestMapping("/transferByAdmin")
    public String transferbyAdmin(@RequestParam("withdrawAccountNumber") int withdrawAccountNumber,
                                  @RequestParam("amount") int amount,
                                  @RequestParam("depositAccountNumber") int depositAccountNumber,
                                  Model model){

        Account withdrawAccount = employeeService.readAccountByAccountNumber(withdrawAccountNumber);
        double withdrawAccountBalance = withdrawAccount.getAccount_balance();
        model.addAttribute("withdrawAccountBalance",withdrawAccountBalance);
        model.addAttribute("amount",amount);

        String operation = "transfer";
        model.addAttribute("operation",operation);


            if(amount <=0){
                return "errorMessagesAdmin";
            }else if(withdrawAccountBalance < amount){
                return "errorMessagesAdmin";
            }
            employeeService.transferByAdmin(withdrawAccount,amount,depositAccountNumber);
        return "staffAccountView";
    }

}

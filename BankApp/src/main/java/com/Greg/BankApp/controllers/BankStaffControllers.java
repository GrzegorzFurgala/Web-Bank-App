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
@SessionAttributes({"login","password","position","employee"})
public class BankStaffControllers {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/employeeCredentialsForm")
    public String employeeCredentialsForm(){
        return "employeeCredentialsForm";
    }
    @RequestMapping("/wrongPassEmployee")
    public String wrongPassEmployee(){
        return "wrongPassEmployee";
    }

    @RequestMapping("/employeeAccountView")
    public String employeeAccountView(@RequestParam("login") String login,
                                      @RequestParam("password") String password,
                                      Model model){
        model.addAttribute("login",login);
        model.addAttribute("password",password);
        BankEmployee employee = employeeService.logInEmployee(login,password);
        model.addAttribute("employee", employee);
        String position = employee.getPosition();
        model.addAttribute("position",position);

        if(employee == null){
            return "redirect:/wrongPassEmployee";
        }
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
    public String approvedAccount(@RequestParam("position") String position,
                                  Model model){

        model.addAttribute("position",position);
        return "approveAccountForm";
    }

    @RequestMapping("/approveAccount/{position}")
    public String approveAccount(@RequestParam("account_number") int accountNumber,
                                 @PathVariable("position") String position,
                                Model model){
        employeeService.approveCustomerBankAccount(accountNumber);

        if(position.equals("employee")){
            model.addAttribute("position",position);
        }else if(position.equals("admin")){
            model.addAttribute("position",position);
        }
        return "staffAccountView";
    }

    @RequestMapping("/notApprovedAccounts")
    public String notApprovedAccounts(Model model){
        List<Account> notApprovedAccounts = employeeService.showNotApprovedAccounts();
        model.addAttribute("notApprovedAccounts",notApprovedAccounts);
        return "notApprovedAccounts";
    }
    //---------------Bank--Admin------------------------------------------------------------

    @RequestMapping("/adminCredentialsForm")
    public String AdminCredentialsForm(){
        return "adminCredentialsForm";
    }

    @RequestMapping("/wrongPassAdmin")
    public String wrongPassAdmin(){
        return "wrongPassAdmin";
    }

    @RequestMapping("/adminAccountView")
    public String AdminAccountView(@RequestParam("login") String login,
                                   @RequestParam("password") String password,
                                   Model model){
        model.addAttribute("login",login);
        model.addAttribute("password",password);
        BankEmployee admin = employeeService.logInAdmin(login,password);
        String position = admin.getPosition();
        model.addAttribute("position",position);


        if(admin == null){
            return "redirect:/wrongPassAdmin";
        }
        return "staffAccountView";
    }

    
    @RequestMapping("/notApprovedAccountsAdmin")
    public String notApprovedAccountsAdmin(Model model){
        List<Account> notApprovedAccounts = employeeService.showNotApprovedAccounts();
        model.addAttribute("notApprovedAccounts",notApprovedAccounts);
        return "notApprovedAccountsAdmin";
    }

    //--------------------DEPOSIT--------------------------------------------------------------

    @RequestMapping("/depositByAdminForm")
    public String depositByAdminForm(){
        return "depositByAdminForm";
    }

    @RequestMapping("/depositNegativeValueAdmin")
    public String depositNegativeValueAdmin(){
        return "depositNegativeValueAdmin";
    }

    @RequestMapping("/depositByAdmin")
    public String depositByAdmin(@RequestParam("accountNumber") int accountNumber,
                                 @RequestParam("amount") double amount){
        if(amount <= 0){
            return "redirect:/depositNegativeValueAdmin";
        }
        employeeService.depositByAdmin(accountNumber,amount);
        return "adminAccountView";
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

            if(amount <= 0){
                return "withdrawErrorMessagesAdmin";
            }else if(newBalance < 0){
                return "withdrawErrorMessagesAdmin";
            }
        employeeService.withdrawByAdmin(account, newBalance);
        return "adminAccountView";
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

            if(amount <=0){
                return "transferErrorMessagesAdmin";
            }else if(withdrawAccountBalance < amount){
                return "transferErrorMessagesAdmin";
            }
            employeeService.transferByAdmin(withdrawAccount,amount,depositAccountNumber);
        return "adminAccountView";
    }

}

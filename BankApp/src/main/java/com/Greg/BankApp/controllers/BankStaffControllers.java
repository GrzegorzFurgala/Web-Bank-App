package com.Greg.BankApp.controllers;
import com.Greg.BankApp.Services.EmployeeService;
import com.Greg.BankApp.domain.Account;
import com.Greg.BankApp.domain.BankAdmin;
import com.Greg.BankApp.domain.BankEmployee;
import com.Greg.BankApp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"login","password"})
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

        if(employee == null){
            return "redirect:/wrongPassEmployee";
        }
        return "employeeAccountView";
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
        return "employeeAccountView";
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
        BankAdmin admin = employeeService.logInAdmin(login,password);
        if(admin == null){
            return "redirect:/wrongPassAdmin";
        }
        return "adminAccountView";
    }

    @RequestMapping("/readAllCustomerAccountAdmin")
    public String allCustomersAdmin(Model model){
        List<Customer> allCustomerList = employeeService.readAllCustomers();
        model.addAttribute("allCustomerList",allCustomerList);
        return "readAllCustomerAccountAdmin";
    }

    @RequestMapping("/readIndividualCustomerFormAdmin")
    public String readIndividualCustomerAccountAdmin(){
        return"readIndividualCustomerFormAdmin";
    }

    @RequestMapping("/readIndividualCustomerAdmin")
    public String readIndividualCustomerAdmin(@RequestParam ("idNumber") String idnumber,
                                              Model model){
            Customer customer = employeeService.readCustomerById(idnumber);
            model.addAttribute("customer", customer);
        return "readIndividualCustomerAdmin";
    }

    @RequestMapping("/readAllBankAccountsAdmin")
    public String allBankAccountsAdmin(Model model) {
        List<Account> allBankAccountList = employeeService.readAllAccounts();
        model.addAttribute("allBankAccounts", allBankAccountList);
        return "readAllBankAccountsAdmin";
    }

    @RequestMapping("/readIndividualBankFormAdmin")
    public String readIndividualBankFormAdmin(){
        return "readIndividualBankFormAdmin";
    }
    @RequestMapping("/readIndividualBankAccountAdmin")
    public String readIndividualBankAccountAdmin(@RequestParam ("account_number") int accountNumber,
                                            Model model){
        Account account = employeeService.readAccountByAccountNumber(accountNumber);
        model.addAttribute("account",account);
        return "readIndividualBankAccountAdmin";
    }
    @RequestMapping("/approveAccountFormAdmin")
    public String approvedAccountAdmin(){
        return "approveAccountFormAdmin";
    }
    @RequestMapping("/approveAccountAdmin")
    public String approveAccountAdmin(@RequestParam("account_number") int accountNumber){
        employeeService.approveCustomerBankAccount(accountNumber);
        return "adminAccountView";
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

    @RequestMapping("/withdrawNegativeValueAdmin")
    public String withdrawNegativeValueAdmin(){
        return "withdrawNegativeValueAdmin";
    }

    @RequestMapping("/lackofFundsWithdrawAdmin")
    public String lackofFundsWithdrawAdmin(){
        return "lackofFundsWithdrawAdmin";
    }

    @RequestMapping("/withdrawByAdmin")
    public String withdrawByAdmin(@RequestParam("accountNumber") int accountNumber,
                                  @RequestParam("amount")  double amount){

        Account account = employeeService.readAccountByAccountNumber(accountNumber);
        double balance = account.getAccount_balance();
        double newBalance = balance - amount;
            if(amount <= 0){
                return "redirect:/withdrawNegativeValueAdmin";
            }else if(newBalance < 0){
                return "redirect:/lackofFundsWithdrawAdmin";
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
                                  @RequestParam("depositAccountNumber") int depositAccountNumber){

        Account withdrawAccount = employeeService.readAccountByAccountNumber(withdrawAccountNumber);
        double withdrawAccountBalance = withdrawAccount.getAccount_balance();

            if(amount <=0){
                return "transferNegativeValueAdmin";
            }else if(withdrawAccountBalance < amount){
               return "transferLackofFundsAdmin";
            }
            employeeService.transferByAdmin(withdrawAccount,amount,depositAccountNumber);
        return "adminAccountView";
    }

}

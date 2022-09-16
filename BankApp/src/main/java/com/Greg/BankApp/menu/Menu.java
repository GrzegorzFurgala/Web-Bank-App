package com.Greg.BankApp.menu;
import com.Greg.BankApp.Services.AccountService;
import com.Greg.BankApp.Services.CustomerService;
import com.Greg.BankApp.Services.EmployeeService;
import com.Greg.BankApp.domain.Account;
import com.Greg.BankApp.domain.BankAdmin;
import com.Greg.BankApp.domain.BankEmployee;
import com.Greg.BankApp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class Menu {

    @Autowired
    AccountService accService;
    @Autowired
    CustomerService cusService;
    @Autowired
    EmployeeService employeeService;
    Scanner sc = new Scanner(System.in);
    LocalDateTime time = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formatedTime = time.format(format);


    public void mainMenu() {
        String login = "";
        String password = "";
        System.out.println("Welcome to the Bank");
        System.out.println("1) log in into Your account");
        System.out.println("2) create new account");
        System.out.println("3) login as bank employee");
        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1:
                System.out.println("Enter Login");
                login = sc.nextLine();
                System.out.println(("Enter password"));
                password = sc.nextLine();
                Customer customerAccount = cusService.logIn(login, password);
                customerAccountView(customerAccount);
                break;
            case 2:
                //todo implementacja rejestracja
                break;
            case 3:
                System.out.println("1) log in as Bank Employee");
                System.out.println("2) log in as Bank Admin");
                System.out.println("3) back to previous menu");
                System.out.println("0) exit");

                int opt = sc.nextInt();
                sc.nextLine();

                switch (opt){
                    case 1:
                        System.out.println("Enter Login");
                        login = sc.nextLine();
                        System.out.println(("Enter password"));
                        password = sc.nextLine();
                        BankEmployee employee = employeeService.logInEmployee(login,password);
                        employeeMenu(employee);
                        break;
                    case 2:
                        System.out.println("Enter Login");
                        login = sc.nextLine();
                        System.out.println(("Enter password"));
                        password = sc.nextLine();
                        BankAdmin admin = employeeService.logInAdmin(login,password);
                        adminMenu(admin);
                        break;
                    case 3:
                        mainMenu();
                        break;
                    case 0:
                        System.exit(1);
                    default: {
                        System.out.println("incorrect selection");
                        System.out.println("Select option from 1 to 3 or 0 for exit");
                        mainMenu();
                    }
            }






        }
    }



    public void customerAccountView(Customer customer){
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Hello: "+customer.getFirstname()+"! Welcome to your bank accounts main profile."+" Time: "+ formatedTime);
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("You have following options: ");
        System.out.println("1) enter your bank account");
        System.out.println("2) apply for new account");
        System.out.println("3) change your personal details");
        System.out.println("0) back to main menu");
        int option = sc.nextInt();
            switch (option){
                case 1:
                    customerBankAccountListView(customer);
                    break;
                case 2:
                    //todo apply for new account
                    break;
                case 3:
                    changePersonalDetails(customer);
                    break;
                case 0:
                    mainMenu();
                    break;
            }
    }


    public void customerBankAccountListView(Customer customer){
        int accountOrderNumber = 1;
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("You have following bank accounts");
        System.out.println("Select which one You would like to use");
        System.out.println("------------------------------------------------------------------------------------------");
        Set<Account> bankAccountList =  customer.getAccountList();
        List<Account> listBankAccountList = bankAccountList.stream().toList();

        for(int i = 0; i < listBankAccountList.size(); i++){
            System.out.println((i+1)+") Account number: "+listBankAccountList.get(i).getAccount_number()
            +" current balance: "+listBankAccountList.get(i).getAccount_balance());
        }
        System.out.println("0) Back to main menu");
        int option = sc.nextInt();

            switch (option){
                case 1:
                    int accountNumber = listBankAccountList.get(0).getAccount_number();
                    accountView(accountNumber,customer);
                    break;
                case 2:
                    accountNumber = listBankAccountList.get(1).getAccount_number();
                    accountView(accountNumber, customer);
                    break;
                case 0:
                    customerAccountView(customer);
                    break;

            }
    }

    public void accountView(int accountNumber,Customer customer){

        Account account = accService.findAccountByAccNumber(accountNumber);
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Account Holder: "+account.getAccount_holder_first_name()+" "+account.getAccount_holder_last_name()
                            +", account number: "+account.getAccount_number()+", account balance: "+account.getAccount_balance());
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("1) deposit");
        System.out.println("2) withdraw");
        System.out.println("3) transfer");
        System.out.println("4) back to previous menu");
        int option = sc.nextInt();

        switch (option){

            case 1:
                accService.deposit(accountNumber,account.getAccount_balance());
                accountView(accountNumber,customer);
                break;
            case 2:
                accService.withdraw(accountNumber,account.getAccount_balance());
                accountView(accountNumber,customer);
                break;
            case 3:
                accService.transfer(accountNumber,account.getAccount_balance());
                accountView(accountNumber,customer);
                break;
            case 0:
                customerBankAccountListView(customer);
                break;
        }

    }

    public void changePersonalDetails(Customer customer){

        System.out.println("--------------------------------------------------------------");
        System.out.println("Select what you would like change");
        System.out.println("--------------------------------------------------------------");
        System.out.println("1) Change first name");
        System.out.println("2) Change last name");
        System.out.println("3) Change city");
        System.out.println("4) Change street");
        System.out.println("5) Change zipcode");
        System.out.println("6) Change telephone");
        System.out.println("7) Change email");
        System.out.println("0) Back to previous menu");
        int option = sc.nextInt();

        switch (option){
            case 1:
                cusService.changeFirstName(customer);
                break;
            case 2:
                cusService.changeLastName(customer);
                break;
            case 3:
                cusService.changeCity(customer);
                break;
            case 4:
                cusService.changeStreet(customer);
                break;
            case 5:
                cusService.changeZipcode(customer);
                break;
            case 6:
                cusService.changeTelephone(customer);
                break;
            case 7:
                cusService.changeEmail(customer);
                break;
            case 0:
                customerAccountView(customer);
                break;
            default:
                System.out.println("Please select option from 1 to 8");
                changePersonalDetails(customer);

        }
    }


    //----------------------------------------Bank---Stuff---Menus--------------------------------//

    public void employeeMenu(BankEmployee employee){
        System.out.println("Hello "+employee.getFirstname()+" You have following option: ");
        System.out.println("------------------------------------------------------");
        System.out.println("1) View all customers details");
        System.out.println("2) View individual customer detail");
        System.out.println("3) View  all bank accounts details");
        System.out.println("4) View individual customer detail");
        System.out.println("5) back to main menu");
        System.out.println("0) exit");
        int option;
        option = sc.nextInt();
        sc.nextLine();
        switch (option){

            case 1:
                employeeService.readAllCustomers();
                employeeMenu(employee);
                break;
            case 2:
                String idCustomer;
                System.out.println("Enter customer ID");
                idCustomer = sc.nextLine();
                employeeService.readCustomerById(idCustomer);
                employeeMenu(employee);
            case 3:
                employeeService.readAllAccounts();
                employeeMenu(employee);
            case 4:
                int accNumber;
                System.out.println("Enter customer ID");
                accNumber = sc.nextInt();
                employeeService.readAccountByAccountNumber(accNumber);
                employeeMenu(employee);
                break;
            case 5:
                mainMenu();
                break;
            case 0:
                System.exit(0);
                break;

            default:{
                System.out.println("Select option from 1 to 5 or 0 for exit");
                employeeMenu(employee);
            }
        }
    }

    public void adminMenu(BankAdmin admin) {
        System.out.println("Hello "+admin.getFirstname()+" You have following option: ");
        System.out.println("------------------------------------------------------");
        System.out.println("1) View all customers details");
        System.out.println("2) View individual customer detail");
        System.out.println("3) View  all bank accounts details");
        System.out.println("4) View individual customer detail");
        System.out.println("5) back to main menu");
        System.out.println("0) exit");
        int option;
        option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                employeeService.readAllCustomers();
                adminMenu(admin);
                break;
            case 2:
                String idCustomer;
                System.out.println("Enter customer ID");
                idCustomer = sc.nextLine();
                employeeService.readCustomerById(idCustomer);
                adminMenu(admin);
            case 3:
                employeeService.readAllAccounts();
                adminMenu(admin);
            case 4:
                int accNumber;
                System.out.println("Enter customer ID");
                accNumber = sc.nextInt();
                employeeService.readAccountByAccountNumber(accNumber);
                adminMenu(admin);
                break;
            case 5:
                mainMenu();
                break;
            case 0:
                System.exit(0);
                break;
            default: {
                System.out.println("Select option from 1 to 5 or 0 for exit");
                adminMenu(admin);
            }
        }
    }




}
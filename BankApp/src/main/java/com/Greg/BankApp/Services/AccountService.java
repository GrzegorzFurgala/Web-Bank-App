package com.Greg.BankApp.Services;
import com.Greg.BankApp.Repositories.AccountRepository;
import com.Greg.BankApp.Starter;
import com.Greg.BankApp.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

@Service
public class AccountService {

    Logger logger = LoggerFactory.getLogger(Starter.class);

    @Autowired
    AccountRepository accountRepo;

    Scanner sc = new Scanner(System.in);


    public Account createNewAccount(){
        System.out.println("Enter account holder first name");
        String account_holder_first_name = sc.nextLine();
        System.out.println("Enter account holder last name");
        String account_holder_last_name = sc.nextLine();
        Account newAccount = new Account(account_holder_first_name,account_holder_last_name);
        accountRepo.createAccount(newAccount);
        return newAccount;
    }
    public Account saveAccount(Account account){
        return accountRepo.saveAccount(account);
    }
    public List<Account> readAllAccount(){
        return accountRepo.readAllAccounts();
    }
    public List<Account> readAllAcceptedAccountsByOwnerId(String ownerId){
        return accountRepo.readAllAcceptedAccountsByOwnerId(ownerId);
    }

    public Account findAccountByAccNumber(int accountNumber){
        return accountRepo.findAccountByAccNumber(accountNumber);
    }

    public void deleteAccountById(int accountNumber){
        accountRepo.deleteAccountById(accountNumber);

    }


    //------------------Account-operations---------------------------//
    //-------------Deposit---withdraw---Transfer---------------------//

    public void deposit(int accountNumber, double balance){
        double newBalance;
        double amount;
        System.out.println("Enter amount to deposit");
        amount = sc.nextDouble();
        newBalance = balance + amount;
        Account account = accountRepo.findAccountByAccNumber(accountNumber);
        account.setAccount_balance(newBalance);
        Account updatedAccount = accountRepo.saveAccount(account);
        System.out.println("Your new balance: "+ updatedAccount.getAccount_balance());
        logger.info("new deposit has been made or account number: "+accountNumber);
        logger.info("amount of deposit: "+amount);
        logger.info("new account balance: "+newBalance );
    }

    public void withdraw(int accountNumber, double balance){
        double newBalance;
        double amount;
        System.out.println("Enter amount to withdraw");
        amount = sc.nextDouble();
        newBalance = balance - amount;

        if(balance < amount){
            System.out.println("There is not enough money on your account");
            System.out.println("Your current account balance is: "+balance);
            System.out.println("Please try again");
            withdraw(accountNumber,balance);
        }else{
            Account account = accountRepo.findAccountByAccNumber(accountNumber);
            account.setAccount_balance(newBalance);
            accountRepo.saveAccount(account);
        }
    }

    public void transfer (int accountNumber, double balance){
        double newBalance;
        double amount;
        int accNumberToTransfer;
        System.out.println("Enter account number for transfer");
        accNumberToTransfer = sc.nextInt();
        System.out.println("Enter the amount for transfer");
        amount = sc.nextDouble();

        newBalance = balance - amount;

        if(balance < amount){
            System.out.println("There is not enough money on your account");
            System.out.println("Your current account balance is: "+balance);
            System.out.println("Please try again");
            transfer(accountNumber,balance);
        }else{
            Account senderAccount = findAccountByAccNumber(accountNumber);
            senderAccount.setAccount_balance(newBalance);
            accountRepo.saveAccount(senderAccount);

            Account receiverBankAccount = findAccountByAccNumber(accNumberToTransfer);
            double reciverBalance = receiverBankAccount.getAccount_balance();
            double updatedReciverBalance = reciverBalance + amount;
            receiverBankAccount.setAccount_balance(updatedReciverBalance);
            accountRepo.saveAccount(receiverBankAccount);
        }


    }




}

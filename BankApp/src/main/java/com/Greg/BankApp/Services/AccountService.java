package com.Greg.BankApp.Services;
import com.Greg.BankApp.Repositories.AccountRepository;
import com.Greg.BankApp.Starter;
import com.Greg.BankApp.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService {

    Logger logger = LoggerFactory.getLogger(Starter.class);
    @Autowired
    AccountRepository accountRepo;

    public void createNewAccount(Account account){
        accountRepo.createAccount(account);

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

    public void deposit(int accountNumber, double balance, double amount){
        double newBalance;
        newBalance = balance + amount;
        Account account = accountRepo.findAccountByAccNumber(accountNumber);
        account.setAccount_balance(newBalance);
        Account updatedAccount = accountRepo.saveAccount(account);

        logger.info("new deposit has been made or account number: "+accountNumber);
        logger.info("amount of deposit: "+amount);
        logger.info("new account balance: "+newBalance );
    }

    public void withdraw(int accountNumber, double balance, double kwota){

        double newBalance;
        newBalance = balance - kwota;
            Account account = accountRepo.findAccountByAccNumber(accountNumber);
            account.setAccount_balance(newBalance);
            accountRepo.saveAccount(account);
    }

    public void transfer (int accountNumber, double balance, double kwota, int receiver){

            double newBalance;
            newBalance = balance - kwota;

            Account senderAccount = findAccountByAccNumber(accountNumber);
            senderAccount.setAccount_balance(newBalance);
            accountRepo.saveAccount(senderAccount);

            Account receiverBankAccount = findAccountByAccNumber(receiver);
            double reciverBalance = receiverBankAccount.getAccount_balance();
            double updatedReciverBalance = reciverBalance + kwota;
            receiverBankAccount.setAccount_balance(updatedReciverBalance);
            accountRepo.saveAccount(receiverBankAccount);
        }
}






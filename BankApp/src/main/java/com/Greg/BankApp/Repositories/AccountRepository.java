package com.Greg.BankApp.Repositories;
import com.Greg.BankApp.domain.Account;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AccountRepository {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("GregPostgres");
    javax.persistence.EntityManager em = factory.createEntityManager();

    public void createAccount(Account account){
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
    }
    public Account saveAccount(Account account){
        em.getTransaction().begin();
        Account updatedAccount =  em.merge(account);
        em.getTransaction().commit();
        return updatedAccount;
    }

    public List<Account> readAllAccounts(){
        List<Account> allAccounts = em.createQuery("Select a from Account a ").getResultList();
        return allAccounts;
    }

    public List<Account> readAllAcceptedAccountsByOwnerId(String ownerId){
        TypedQuery<Account> allAccounts = em.createQuery("Select a from Account a where a.owner_id=:owner and account_approved = true",Account.class);
        allAccounts.setParameter("owner",ownerId);
        List<Account> customerApprovedBankAccounts = allAccounts.getResultList();
        return customerApprovedBankAccounts;
    }

    public Account findAccountByAccNumber(int account_number){
        return em.find(Account.class, account_number);
    }

    public void deleteAccountById(int account_number){
        Account cus = findAccountByAccNumber(account_number);
        em.getTransaction().begin();
        em.remove(cus);
        em.getTransaction().commit();
    }

}

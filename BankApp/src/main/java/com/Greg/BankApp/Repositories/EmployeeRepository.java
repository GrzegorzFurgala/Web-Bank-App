package com.Greg.BankApp.Repositories;
import com.Greg.BankApp.domain.Account;
import com.Greg.BankApp.domain.BankAdmin;
import com.Greg.BankApp.domain.BankEmployee;
import com.Greg.BankApp.domain.Customer;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

@Repository
public class EmployeeRepository {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("GregPostgres");
    javax.persistence.EntityManager em = factory.createEntityManager();

    public void persist(BankEmployee employee) {
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
    }

    public BankEmployee logInEmployee(String login, String password,String position){
            try {
                TypedQuery<BankEmployee> employee = em.createQuery("Select e from BankEmployee e where e.login=:login and e.password=:password and e.position=:position", BankEmployee.class);
                employee.setParameter("login", login);
                employee.setParameter("password", password);
                employee.setParameter("position", position);
                return employee.getSingleResult();
            }catch (NoResultException e){
                System.out.println("podany login lub haslo sa nieprawidlowe, sprobuj ponownie");
            }
            return null;
    }

    public void persist(BankAdmin bankAdmin) {
        em.getTransaction().begin();
        em.persist(bankAdmin);
        em.getTransaction().commit();
    }
    public BankEmployee updateEmployee(BankEmployee employee) {
        em.getTransaction().begin();
        BankEmployee emp = em.merge(employee);
        em.getTransaction().commit();
        return emp;
    }
    public BankAdmin updateEmployee(BankAdmin bankAdmin) {
        em.getTransaction().begin();
        BankAdmin emp = em.merge(bankAdmin);
        em.getTransaction().commit();
        return emp;
    }
    public BankEmployee findBankEmployeeById(int idEmployee){
        BankEmployee employee = em.find(BankEmployee.class,idEmployee);
        return employee;
    }

    public void deleteEmployeeById(int idEmployee) {
        em.getTransaction().begin();
        BankEmployee employee = findBankEmployeeById(idEmployee);
        em.remove(employee);
        em.getTransaction().commit();
    }

    public  BankAdmin findAdminById(int idAdmin){
        BankAdmin admin = em.find(BankAdmin.class, idAdmin);
        return admin;
    }
    public void deleteAdminById(int idAdmin) {
        em.getTransaction().begin();
        BankAdmin admin =findAdminById(idAdmin);
        em.remove(admin);
        em.getTransaction().commit();
    }

    public List<Customer> readAllCustomers(){
        List<Customer> customerList = em.createQuery("Select c from Customer c").getResultList();
        return customerList;
    }

    public Customer readCustomerById(String idnumber) {
        Customer customer = em.find(Customer.class,idnumber);
        return customer;
    }

    public List<Account> readAllAccounts() {
        List<Account> accountList = em.createQuery("select a from Account a").getResultList();
        return accountList;
    }

    public Account readAccountByAccountNumber(int accountNumber) {
        Account account = em.find(Account.class, accountNumber);
        return account;
    }
    public Account updateAccount(Account account){
        Account acc = em.merge(account);
        return acc;
    }

    public void approveCustomerAccount(int accountNumber){
        Account account = readAccountByAccountNumber(accountNumber);
        account.setAccount_approved(true);
        em.getTransaction().begin();
        updateAccount(account);
        em.getTransaction().commit();
    }

    public void disapproveCustomerAccount(int accountNumber){
        Account account = readAccountByAccountNumber(accountNumber);
        account.setAccount_approved(false);
        em.getTransaction().begin();
        updateAccount(account);
        em.getTransaction().commit();
    }

    public BankEmployee logInAdmin(String login, String password,String position){
        try {
            TypedQuery<BankEmployee> employee = em.createQuery("Select e from BankEmployee e where e.login=:login and e.password=:password and e.position=:position", BankEmployee.class);
            employee.setParameter("login", login);
            employee.setParameter("password", password);
            employee.setParameter("position", position);
            return employee.getSingleResult();
        }catch (NoResultException e){
            System.out.println("podany login lub haslo sa nieprawidlowe, sprobuj ponownie");
        }
        return null;
    }





    /*
    public BankAdmin logInAdmin(String login, String password) {
        try {
            TypedQuery<BankAdmin> admin = em.createQuery("select a from BankAdmin a where a.login=:loginAdmin and a.password=:pass", BankAdmin.class);
            admin.setParameter("loginAdmin", login);
            admin.setParameter("pass", password);
            return admin.getSingleResult();
        }catch (NoResultException e){
            System.out.println("podany login lub haslo sa nieprawidlowe, sprobuj ponownie");
            //System.exit(1);
        }
        return null;
    }
    */
    public List<Account> showNotApprovedAccounts(){
        TypedQuery<Account> notApprovedAccout = em.createQuery("select a from Account a where a.account_approved = false",Account.class);
        return notApprovedAccout.getResultList();
    }
}

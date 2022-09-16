package com.Greg.BankApp.Repositories;
import com.Greg.BankApp.domain.Customer;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomerRepository {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("GregPostgres");
    javax.persistence.EntityManager em = factory.createEntityManager();

    public CustomerRepository() {
    }

    public Customer updateCustomer(Customer customer) {
        em.getTransaction().begin();
        Customer cus = em.merge(customer);
        em.getTransaction().commit();
        return cus;
    }

    public void createCustomer(Customer customer){
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
    }

    public Customer readCustomerById(String idnumber){
       return  em.find(Customer.class, idnumber);
    }

    public List<Customer> readAllCustomers(){

        List<Customer> allCustomers = em.createQuery("Select s from Customer s ").getResultList();
        allCustomers.stream().forEach(customer -> System.out.println(customer));
        return allCustomers;
    }

    public void deleteCustomerById(String idnumber){
        Customer cus = readCustomerById(idnumber);
        em.getTransaction().begin();
        em.remove(cus);
        em.getTransaction().commit();
    }

    public Customer logIn(String login, String password) {

        try {
            TypedQuery<Customer> kontoKlienta = em.createQuery("select c from Customer c where c.login =:customerLogin and c.password=: pass", Customer.class);
            kontoKlienta.setParameter("customerLogin", login);
            kontoKlienta.setParameter("pass", password);
            return kontoKlienta.getSingleResult();
        }catch(NoResultException e){
            System.out.println("podany login lub haslo sa nieprawidlowe, sprobuj ponownie");
            System.exit(1);
        }
        return null;
    }





}

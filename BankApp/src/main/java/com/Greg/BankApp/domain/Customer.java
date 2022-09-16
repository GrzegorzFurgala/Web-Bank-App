package com.Greg.BankApp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
public class Customer {

    @Id
    private String idnumber;
    private String firstname;
    private String lastname;
    private String street;
    private String city;
    private String zipcode;
    private String email;
    private String telephone;
    private String login;
    private String password;
    @OneToMany
    @JoinColumn(name = "owner_id")
    private Set<Account> accountList;

    public Customer(){
    }

    public Customer(String idNumber, String firstName, String lastName, String street, String city,
                    String zipCode, String email, String telephone,String login,String password) {
        this.idnumber = idNumber;
        this.firstname = firstName;
        this.lastname = lastName;
        this.street = street;
        this.city = city;
        this.zipcode = zipCode;
        this.email = email;
        this.telephone = telephone;
        this.login = login;
        this.password=password;
        this.accountList = new HashSet<>();
    }


    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Account> getAccountList() {
        return accountList;
    }

    public void addAccountToTheList(Account account) {
        accountList.add(account);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idnumber='" + idnumber + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", accountList=" + accountList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(idnumber, customer.idnumber) && Objects.equals(firstname, customer.firstname) && Objects.equals(lastname, customer.lastname) && Objects.equals(street, customer.street) && Objects.equals(city, customer.city) && Objects.equals(zipcode, customer.zipcode) && Objects.equals(email, customer.email) && Objects.equals(telephone, customer.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idnumber, firstname, lastname, street, city, zipcode, email, telephone);
    }
}

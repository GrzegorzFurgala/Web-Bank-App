package com.Greg.BankApp.domain;
import javax.persistence.*;

@Entity
@Table(name = "bank_employees")
public class BankEmployee{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idemployee;
    String firstname;
    String lastname;
    String login;
    String password;
    String position;

    public BankEmployee(){}

    public BankEmployee(String firstname, String lastname, String login, String password,String position) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.position = position;
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

    public String getPossition() {
        return position;
    }

    public void setPossition(String possition) {
        this.position = possition;
    }

    @Override
    public String toString() {
        return "BankEmployee{" +
                "idemployee=" + idemployee +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", possition='" + position + '\'' +
                '}';
    }
}

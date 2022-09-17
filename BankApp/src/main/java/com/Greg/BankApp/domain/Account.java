package com.Greg.BankApp.domain;
import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int account_number;
    private String account_holder_first_name;
    private String account_holder_last_name;
    private double account_balance;
    boolean account_approved;
    String owner_id;



    public Account() {
    }

    public Account(String account_holder_first_name, String account_holder_last_name) {
        this.account_holder_first_name = account_holder_first_name;
        this.account_holder_last_name = account_holder_last_name;
        this.account_approved = false;
        this.account_balance = 1000;
    }

    public boolean isAccount_approved() {
        return account_approved;
    }

    public void setAccount_approved(boolean account_approved) {
        this.account_approved = account_approved;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public String getAccount_holder_first_name() {
        return account_holder_first_name;
    }

    public void setAccount_holder_first_name(String account_holder_first_name) {
        this.account_holder_first_name = account_holder_first_name;
    }

    public String getAccount_holder_last_name() {
        return account_holder_last_name;
    }

    public void setAccount_holder_last_name(String account_holder_last_name) {
        this.account_holder_last_name = account_holder_last_name;
    }

    public double getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(double account_balance) {
        this.account_balance = account_balance;
    }


    @Override
    public String toString() {
        return "Account{" +
                "account_number=" + account_number +
                ", account_holder_first_name='" + account_holder_first_name + '\'' +
                ", account_holder_last_name='" + account_holder_last_name + '\'' +
                ", account_balance=" + account_balance +
                '}';
    }
}

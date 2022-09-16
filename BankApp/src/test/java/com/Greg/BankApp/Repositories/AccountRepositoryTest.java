package com.Greg.BankApp.Repositories;

import com.Greg.BankApp.domain.Account;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {

    AccountRepository accRepo = new AccountRepository();

    @Test
    void theResultShouldBeNotNullAfterReadAllAccountsFromDatabase() {
        //given + when
        List<Account> accountList = accRepo.readAllAccounts();
        //then
        assertNotNull(accountList);
    }

    @Test
    void theResultShouldBeNotNullAfterReadOneAccountByIdFromDatabase() {
        //given + when
        Account acc = accRepo.findAccountByAccNumber(56);
        //then
        assertNotNull(acc);

    }
}
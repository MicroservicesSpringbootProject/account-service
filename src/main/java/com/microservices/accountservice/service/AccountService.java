package com.microservices.accountservice.service;

import com.microservices.accountservice.entity.Account;
import com.microservices.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account findAccountByNumber(String accountNumber) {
        return accountRepository.findByNumber(accountNumber);
    }

    public List<Account> findAccountsByOwnerContainingIgnoreCase(String partialName) {
        return accountRepository.findAccountsByOwnerContainingIgnoreCase(partialName);
    }

    public Account saveAccount(Account account) {
        accountRepository.save(account);
        return account;
    }
}

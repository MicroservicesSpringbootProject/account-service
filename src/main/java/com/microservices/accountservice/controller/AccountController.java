package com.microservices.accountservice.controller;

import com.microservices.accountservice.entity.Account;
import com.microservices.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/addAccount")
    public Account addAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @GetMapping("/{accountNumber}")
    public Account findAccountByNumber(@PathVariable("accountNumber") String accountNumber) throws AccountNotFoundException {
        Account account = accountService.findAccountByNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        } else {
            return account;
        }
    }

    @GetMapping("/owner/{name}")
    public List<Account> findAccountByOwner(@PathVariable("name") String partialName) throws AccountNotFoundException {
        List<Account> accounts = accountService.findAccountsByOwnerContainingIgnoreCase(partialName);
        if (accounts == null || accounts.size() == 0) {
            throw new AccountNotFoundException(partialName);
        } else {
            return accounts;
        }
    }
}

package com.springrestapi.banking_app.controller;

import com.springrestapi.banking_app.dto.AccountDto;
import com.springrestapi.banking_app.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController("/api/banking")
@RequiredArgsConstructor
public class BankingController {

    private final AccountServiceImpl accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        log.info("Fetching all accounts");
        List<AccountDto> accounts = accountService.getAllAccounts();
        return accounts.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(accounts);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id) {
        log.info("Fetching account with ID: {}", id);
        AccountDto account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/account")
    public ResponseEntity<String> createAccount(@RequestBody AccountDto accountDto) {
        log.info("Create account endpoint called");
        AccountDto savedAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>("Account created: " + savedAccount, HttpStatus.CREATED);
    }

    @PutMapping("/account/deposit/{id}")
    public ResponseEntity<AccountDto> depositToAccount(@PathVariable("id") Long id, @RequestParam Double amount) {
        log.info("Depositing amount: {} to account ID: {}", amount, id);
        AccountDto updatedAccount = accountService.deposit(id, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    @PutMapping("/account/withdrawal/{id}")
    public ResponseEntity<AccountDto> withdrawFromAccount(@PathVariable("id") Long id, @RequestParam Double amount) {
        log.info("Withdrawing amount: {} from account ID: {}", amount, id);
        AccountDto updatedAccount = accountService.withdrawal(id, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id) {
        log.info("Deleting account with ID: {}", id);
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account with ID " + id + " deleted successfully.");
    }
}

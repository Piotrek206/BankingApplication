package com.springrestapi.banking_app.service;

import com.springrestapi.banking_app.dto.AccountDto;
import com.springrestapi.banking_app.entity.Account;

import java.util.List;

public interface AccountService {

    List<AccountDto> getAllAccounts();

    AccountDto getAccountById(Long id);

    AccountDto createAccount(AccountDto accountDto);

    AccountDto deposit(Long id, Double amount);

    AccountDto withdrawal(Long id, Double amount);

    void deleteAccount(Long id);
}

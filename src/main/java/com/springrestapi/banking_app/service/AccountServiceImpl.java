package com.springrestapi.banking_app.service;

import com.springrestapi.banking_app.dto.AccountDto;
import com.springrestapi.banking_app.entity.Account;
import com.springrestapi.banking_app.exception.AccountNotFoundException;
import com.springrestapi.banking_app.mapper.AccountMapper;
import com.springrestapi.banking_app.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        return accountMapper.toDto(account);
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountHolderName(accountDto.getAccountHolderName());
        account.setBalance(accountDto.getBalance());
        Account savedAccount = accountRepository.save(account);

        return accountMapper.toDto(savedAccount);
    }

    @Override
    public AccountDto deposit(Long id, Double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        account.setBalance(account.getBalance() + amount);
        Account updatedAccount = accountRepository.save(account);
        return accountMapper.toDto(updatedAccount);
    }

    @Override
    public AccountDto withdrawal(Long id, Double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        account.setBalance(account.getBalance() - amount);
        Account updatedAccount = accountRepository.save(account);
        return accountMapper.toDto(updatedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException(id);
        }
        accountRepository.deleteById(id);
    }
}

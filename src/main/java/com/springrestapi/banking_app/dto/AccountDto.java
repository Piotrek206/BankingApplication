package com.springrestapi.banking_app.dto;

public record AccountDto (
    Long id,
    String accountNumber,
    String accountHolderName,
    Double balance
) {}

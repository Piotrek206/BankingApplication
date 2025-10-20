package com.springrestapi.banking_app.mapper;

import com.springrestapi.banking_app.dto.AccountDto;
import com.springrestapi.banking_app.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto toDto(Account account);

    Account toEntity(AccountDto accountDto);
}

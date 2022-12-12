package com.example.bpmn_task.mapper;



import com.example.bpmn_task.dto.request.AccountRequest;
import com.example.bpmn_task.dto.response.AccountResponse;
import com.example.bpmn_task.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {
    AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "customer",target = "customerResponse")
    AccountResponse toAccountResponse(Account account);

    Account toAccount(AccountRequest accountRequest);

    List<AccountResponse> toAccountResponseList(List<Account> accountList);

    AccountRequest toAccountRequest(Account account);




}

package com.example.bpmn_task.service;


import com.example.bpmn_task.dto.request.AccountRequest;
import com.example.bpmn_task.dto.response.AccountResponse;
import com.example.bpmn_task.entity.Account;


import java.util.List;


public interface AccountService {
    List<AccountResponse> getAllAccount();
    AccountResponse addAccount(AccountRequest accountRequest);
    AccountResponse updateAccount(AccountRequest accountRequest, Account account);
    AccountResponse deleteAccount(Account account);


}

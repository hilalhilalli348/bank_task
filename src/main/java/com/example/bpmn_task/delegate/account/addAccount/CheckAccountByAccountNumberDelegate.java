package com.example.bpmn_task.delegate.account.addAccount;

import com.example.bpmn_task.dto.request.AccountRequest;


import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.repository.AccountRepository;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("checkAccountByAccountNumberDelegate")
public class CheckAccountByAccountNumberDelegate implements JavaDelegate {


    private final AccountRepository accountRepository;


    @Autowired
    public CheckAccountByAccountNumberDelegate(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void execute(DelegateExecution execution) throws Exception {

        AccountRequest accountRequest = (AccountRequest) execution.getVariable("accountRequest");
        String accountNumber = accountRequest.getAccountNumber();
        Account account = accountRepository.findByAccountNumber(accountNumber).orElse(null);

        execution.setVariable("account",account);
        execution.setVariable("status", Status.of(ErrorEnum.ACCOUNT_IS_EXIST.getStatus(), ErrorEnum.ACCOUNT_IS_EXIST.getMessage()));


    }
}

package com.example.bpmn_task.delegate.account;

import camundajar.impl.scala.concurrent.impl.FutureConvertersImpl;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.repository.AccountRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("checkAccountDelegate")
public class CheckAccountDelegate implements JavaDelegate {
    private final AccountRepository accountRepository;

    @Autowired
    public CheckAccountDelegate(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long accountID = (Long) execution.getVariable("accountID");
        Account account = accountRepository.findById(accountID).orElse(null);

        execution.setVariable("account", account);
        execution.setVariable("status", Status.of(ErrorEnum.ACCOUNT_NOT_FOUND.getStatus(), ErrorEnum.ACCOUNT_NOT_FOUND.getMessage()));
    }
}

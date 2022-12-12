package com.example.bpmn_task.delegate.transferMoney;

import com.example.bpmn_task.dto.request.TransferMoneyRequest;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.repository.AccountRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("checkAccountNumberDelegate")
public class CheckAccountNumberDelegate implements JavaDelegate {
    private final AccountRepository accountRepository;

    @Autowired
    public CheckAccountNumberDelegate(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TransferMoneyRequest transferMoneyRequest = (TransferMoneyRequest) execution.getVariable("transferMoneyRequest");

        String creditorAccountNumber = transferMoneyRequest.getCreditorAccountNumber();
        String debitororAccountNumber = transferMoneyRequest.getDebitorAccountNumber();

        Account creditorAccount = accountRepository.findByAccountNumber(creditorAccountNumber).orElse(null);
        Account debitorAccount = accountRepository.findByAccountNumber(debitororAccountNumber).orElse(null);



        execution.setVariable("creditorAccount", creditorAccount);
        execution.setVariable("debitorAccount", debitorAccount);


    }
}

package com.example.bpmn_task.delegate.account.removeAccount;

import com.example.bpmn_task.dto.response.AccountResponse;
import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.service.AccountService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("removeAccountDelegate")
public class RemoveAccountDelegate implements JavaDelegate {

    private final AccountService accountService;

    @Autowired
    public RemoveAccountDelegate(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Account account = (Account) execution.getVariable("account");

        AccountResponse accountResponse = accountService.deleteAccount(account);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(Status.of(ErrorEnum.SUCCESS.getStatus(), ErrorEnum.SUCCESS.getMessage()));
        commonResponse.setHttpStatusCode(HttpStatus.OK.value());
        commonResponse.setData(accountResponse);

        execution.setVariable("response", commonResponse);

    }
}

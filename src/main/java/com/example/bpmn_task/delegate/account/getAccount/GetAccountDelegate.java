package com.example.bpmn_task.delegate.account.getAccount;

import com.example.bpmn_task.dto.response.AccountResponse;
import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.Status;

import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.mapper.AccountMapper;
import com.example.bpmn_task.service.AccountService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("getAccountDelegate")
public class GetAccountDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Account account = (Account) execution.getVariable("account");

        AccountResponse accountResponse = AccountMapper.MAPPER.toAccountResponse(account);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(Status.of(ErrorEnum.SUCCESS.getStatus(), ErrorEnum.SUCCESS.getMessage()));
        commonResponse.setHttpStatusCode(HttpStatus.OK.value());
        commonResponse.setData(accountResponse);

        execution.setVariable("response", commonResponse);
    }
}

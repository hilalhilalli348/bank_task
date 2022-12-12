package com.example.bpmn_task.delegate.account.addAccount;

import com.example.bpmn_task.dto.request.AccountRequest;
import com.example.bpmn_task.dto.response.AccountResponse;
import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.mapper.AccountMapper;
import com.example.bpmn_task.repository.AccountRepository;
import com.example.bpmn_task.repository.CustomerRepository;
import com.example.bpmn_task.service.AccountService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("addAccountDelegate")
public class AddAccountDelegate implements JavaDelegate {



    private final AccountService accountService;

    @Autowired
    public AddAccountDelegate(AccountService accountService) {

        this.accountService = accountService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        AccountRequest accountRequest = (AccountRequest) execution.getVariable("accountRequest");
        AccountResponse accountResponse = accountService.addAccount(accountRequest);


        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(accountResponse);
        commonResponse.setStatus(Status.of(ErrorEnum.SUCCESS.getStatus(),ErrorEnum.SUCCESS.getMessage()));
        commonResponse.setHttpStatusCode(HttpStatus.OK.value());

        execution.setVariable("response",commonResponse);
    }


}

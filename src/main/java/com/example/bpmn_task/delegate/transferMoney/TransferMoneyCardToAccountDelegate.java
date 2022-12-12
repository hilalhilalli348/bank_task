package com.example.bpmn_task.delegate.transferMoney;

import com.example.bpmn_task.dto.request.TransferMoneyRequest;
import com.example.bpmn_task.dto.response.AccountCardMoneyContainer;
import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.dto.response.TransferResponse;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.entity.Card;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.service.TransferMoneyService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("transferMoneyCardToAccountDelegate")
public class TransferMoneyCardToAccountDelegate implements JavaDelegate {
    private final TransferMoneyService transferMoneyService;

    @Autowired
    public TransferMoneyCardToAccountDelegate(TransferMoneyService transferMoneyService) {
        this.transferMoneyService = transferMoneyService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TransferMoneyRequest transferMoneyRequest = (TransferMoneyRequest) execution.getVariable("transferMoneyRequest");
        Card creditorCard = (Card) execution.getVariable("card");
        Account debitorAccount = (Account) execution.getVariable("account");

        AccountCardMoneyContainer accountCardMoneyContainer = new AccountCardMoneyContainer();
        accountCardMoneyContainer.setCreditorCard(creditorCard).setDebitorAccount(debitorAccount);
        accountCardMoneyContainer.setOutgoingMoney(transferMoneyRequest.getOutgoingMoney());

        TransferResponse transferResponse = transferMoneyService.transferMoneyCardToAccount(accountCardMoneyContainer);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(Status.of(ErrorEnum.SUCCESS.getStatus(), ErrorEnum.SUCCESS.getMessage()));
        commonResponse.setHttpStatusCode(HttpStatus.OK.value());
        commonResponse.setData(transferResponse);

        execution.setVariable("response", commonResponse);

    }
}

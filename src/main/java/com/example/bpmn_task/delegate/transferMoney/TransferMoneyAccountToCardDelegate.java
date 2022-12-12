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

@Component("transferMoneyAccountToCardDelegate")
public class TransferMoneyAccountToCardDelegate implements JavaDelegate {
    private final TransferMoneyService transferMoneyService;

    @Autowired
    public TransferMoneyAccountToCardDelegate(TransferMoneyService transferMoneyService) {
        this.transferMoneyService = transferMoneyService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TransferMoneyRequest transferMoneyRequest = (TransferMoneyRequest) execution.getVariable("transferMoneyRequest");

        Account creditorAccount = (Account) execution.getVariable("account");
        Card debitorCard = (Card) execution.getVariable("card");

        AccountCardMoneyContainer accountCardMoneyContainer = new AccountCardMoneyContainer();
        accountCardMoneyContainer.setCreditorAccount(creditorAccount).setDebitorCard(debitorCard);
        accountCardMoneyContainer.setOutgoingMoney(transferMoneyRequest.getOutgoingMoney());

        TransferResponse transferResponse = transferMoneyService.transferMoneyAccountToCard(accountCardMoneyContainer);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(Status.of(ErrorEnum.SUCCESS.getStatus(), ErrorEnum.SUCCESS.getMessage()));
        commonResponse.setHttpStatusCode(HttpStatus.OK.value());
        commonResponse.setData(transferResponse);

        execution.setVariable("response", commonResponse);
    }
}

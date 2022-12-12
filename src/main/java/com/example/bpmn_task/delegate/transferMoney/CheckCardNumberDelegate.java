package com.example.bpmn_task.delegate.transferMoney;


import com.example.bpmn_task.dto.request.TransferMoneyRequest;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Card;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.repository.CardRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("checkCardNumberDelegate")
public class CheckCardNumberDelegate implements JavaDelegate {

    private final CardRepository cardRepository;

    @Autowired
    public CheckCardNumberDelegate(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }



    @Override
    public void execute(DelegateExecution execution) throws Exception {

        TransferMoneyRequest transferMoneyRequest = (TransferMoneyRequest) execution.getVariable("transferMoneyRequest");

         Long creditorCardNumber = transferMoneyRequest.getCreditorCardNumber();
         Long debitorCardNumber = transferMoneyRequest.getDebitorCardNumber();

        Card creditorCard = cardRepository.findByCardNumber(creditorCardNumber).orElse(null);
        Card debitorCard = cardRepository.findByCardNumber(debitorCardNumber).orElse(null);

        execution.setVariable("creditorCard",creditorCard);
        execution.setVariable("debitorCard",debitorCard);
    }
}

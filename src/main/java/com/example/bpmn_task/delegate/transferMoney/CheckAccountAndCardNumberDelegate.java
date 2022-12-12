package com.example.bpmn_task.delegate.transferMoney;

import com.example.bpmn_task.dto.request.TransferMoneyRequest;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.entity.Card;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.enums.TransferTypeEnum;
import com.example.bpmn_task.repository.AccountRepository;
import com.example.bpmn_task.repository.CardRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component("checkAccountAndCardNumberDelegate")
public class CheckAccountAndCardNumberDelegate implements JavaDelegate {
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    @Autowired
    public CheckAccountAndCardNumberDelegate(AccountRepository accountRepository, CardRepository cardRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TransferMoneyRequest transferMoneyRequest = (TransferMoneyRequest) execution.getVariable("transferMoneyRequest");

        String accountNumber =null;
        Long cardNumber =null;

        if (transferMoneyRequest.getTransferType()== TransferTypeEnum.ACCOUNT_TO_CARD){
            accountNumber = transferMoneyRequest.getCreditorAccountNumber();
            cardNumber = transferMoneyRequest.getDebitorCardNumber();

            Account creditorAccount = accountRepository.findByAccountNumber(accountNumber).orElse(null);
            Card debitorAccout = cardRepository.findByCardNumber(cardNumber).orElse(null);


            execution.setVariable("account",creditorAccount);
            execution.setVariable("card",debitorAccout);

        }
        else if (transferMoneyRequest.getTransferType()==TransferTypeEnum.CARD_TO_ACCOUNT){
            cardNumber = transferMoneyRequest.getCreditorCardNumber();
            accountNumber = transferMoneyRequest.getDebitorAccountNumber();

            Card creditorCard = cardRepository.findByCardNumber(cardNumber).orElse(null);
            Account debitorAccount = accountRepository.findByAccountNumber(accountNumber).orElse(null);

            execution.setVariable("card",creditorCard);
            execution.setVariable("account",debitorAccount);
        }



    }


}

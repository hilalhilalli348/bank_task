package com.example.bpmn_task.delegate.transferMoney;

import com.example.bpmn_task.dto.request.TransferMoneyRequest;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.entity.Card;
import com.example.bpmn_task.enums.AccountStatusEnum;
import com.example.bpmn_task.enums.CardStatusEnum;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.exception.BaseException;
import com.example.bpmn_task.repository.AccountRepository;
import com.example.bpmn_task.repository.CardRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("checkStatusAndActivityAndBalanceDelegate")
public class CheckStatusAndActivityAndBalanceDelegate implements JavaDelegate {
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    @Autowired
    public CheckStatusAndActivityAndBalanceDelegate(AccountRepository accountRepository, CardRepository cardRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        TransferMoneyRequest transferMoneyRequest = (TransferMoneyRequest) execution.getVariable("transferMoneyRequest");

        // transfer-in tipine gore status , activie və köçürmə edəcək balansın kifayətliliyi yoxlanılır

        switch (transferMoneyRequest.getTransferType()) {

            case ACCOUNT_TO_ACCOUNT: {

                Account creditorAccount = (Account) execution.getVariable("creditorAccount");
                Account debitorAccount = (Account) execution.getVariable("debitorAccount");


                // creditorun balansının kifayətliliyi yoxlanılır
                if (creditorAccount.getBalance().compareTo(transferMoneyRequest.getOutgoingMoney()) <0) {
                    throw BaseException.of(ErrorEnum.NOT_ENOUGH_BALANCE, HttpStatus.UNPROCESSABLE_ENTITY.value());
                }

                if (creditorAccount.getActive() == 0 || debitorAccount.getActive() == 0) {
                    throw BaseException.of(ErrorEnum.ACCOUNT_DEACTIVATED, HttpStatus.FORBIDDEN.value());
                }

                if (creditorAccount.getStatus() == AccountStatusEnum.BLOCK || debitorAccount.getStatus() == AccountStatusEnum.BLOCK) {
                    throw BaseException.of(ErrorEnum.ACCOUNT_BLOCKED, HttpStatus.UNPROCESSABLE_ENTITY.value());
                }

            }

            break;
            case ACCOUNT_TO_CARD: {


                Account creditorAccount = (Account) execution.getVariable("account");
                Card debitorCard = (Card) execution.getVariable("card");

                // creditorun balansının kifayətliliyi yoxlanılır
                if (creditorAccount.getBalance().compareTo(transferMoneyRequest.getOutgoingMoney()) < 0) {
                    throw BaseException.of(ErrorEnum.NOT_ENOUGH_BALANCE, HttpStatus.UNPROCESSABLE_ENTITY.value());
                }


                if (creditorAccount.getActive() == 0) {
                    throw BaseException.of(ErrorEnum.ACCOUNT_DEACTIVATED, HttpStatus.FORBIDDEN.value());
                }
                if (debitorCard.getActive() == 0) {
                    throw BaseException.of(ErrorEnum.CARD_DEACTIVATED, HttpStatus.FORBIDDEN.value());
                }

                if (creditorAccount.getStatus() == AccountStatusEnum.BLOCK) {
                    throw BaseException.of(ErrorEnum.ACCOUNT_BLOCKED, HttpStatus.UNPROCESSABLE_ENTITY.value());
                }

                if (debitorCard.getStatus() == CardStatusEnum.BLCOK) {
                    throw BaseException.of(ErrorEnum.CARD_BLOCKED, HttpStatus.UNPROCESSABLE_ENTITY.value());
                }

            }
            break;

            case CARD_TO_ACCOUNT: {


                Card creditorCard = (Card) execution.getVariable("card");
                Account debitorAccount = (Account) execution.getVariable("account");

                // creditorun balansının kifayətliliyi yoxlanılır
                if (creditorCard.getBalance().compareTo(transferMoneyRequest.getOutgoingMoney()) < 0) {
                    throw BaseException.of(ErrorEnum.NOT_ENOUGH_BALANCE, HttpStatus.UNPROCESSABLE_ENTITY.value());
                }

                if (creditorCard.getActive() == 0) {
                    throw BaseException.of(ErrorEnum.ACCOUNT_DEACTIVATED, HttpStatus.FORBIDDEN.value());
                }
                if (debitorAccount.getActive() == 0) {
                    throw BaseException.of(ErrorEnum.CARD_DEACTIVATED, HttpStatus.FORBIDDEN.value());
                }

                if (debitorAccount.getStatus() == AccountStatusEnum.BLOCK) {
                    throw BaseException.of(ErrorEnum.ACCOUNT_BLOCKED, HttpStatus.UNPROCESSABLE_ENTITY.value());
                }

                if (creditorCard.getStatus() == CardStatusEnum.BLCOK) {
                    throw BaseException.of(ErrorEnum.CARD_BLOCKED, HttpStatus.UNPROCESSABLE_ENTITY.value());
                }

            }
            break;

            case CARD_TO_CARD: {

                Card creditorCard = (Card) execution.getVariable("creditorCard");
                Card debitorCard = (Card) execution.getVariable("debitorCard");

                if (creditorCard.getActive() == 0 || debitorCard.getActive() == 0) {
                    throw BaseException.of(ErrorEnum.CARD_DEACTIVATED, HttpStatus.FORBIDDEN.value());
                }

                // creditorun balansının kifayətliliyi yoxlanılır
                if (creditorCard.getBalance().compareTo(transferMoneyRequest.getOutgoingMoney()) < 0) {
                    throw BaseException.of(ErrorEnum.NOT_ENOUGH_BALANCE, HttpStatus.UNPROCESSABLE_ENTITY.value());
                }

                if (creditorCard.getStatus() == CardStatusEnum.BLCOK || debitorCard.getStatus() == CardStatusEnum.BLCOK) {
                    throw BaseException.of(ErrorEnum.ACCOUNT_BLOCKED, HttpStatus.UNPROCESSABLE_ENTITY.value());
                }

            }
            break;

        }


    }
}

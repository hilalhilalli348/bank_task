package com.example.bpmn_task.service.transferMoneyStrategies;

import com.example.bpmn_task.dto.response.AccountCardMoneyContainer;
import com.example.bpmn_task.dto.response.TransferResponse;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.entity.Card;
import com.example.bpmn_task.entity.Transfer;
import com.example.bpmn_task.enums.*;
import com.example.bpmn_task.mapper.TransferMapper;
import com.example.bpmn_task.repository.AccountRepository;
import com.example.bpmn_task.repository.CardRepository;
import com.example.bpmn_task.repository.TransferRepository;
import com.example.bpmn_task.utils.CurrencyScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;

@Component
public class TransferMoneyCardToCardStrategy implements TransferMoneyStrategy{

    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    @Autowired
    public TransferMoneyCardToCardStrategy(  CardRepository cardRepository, AccountRepository accountRepository, TransferRepository transferRepository) {

        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }


    @Transactional
    @Override
    public TransferResponse transfer(AccountCardMoneyContainer accountCardMoneyContainer) {

        Card creditorCard = accountCardMoneyContainer.getCreditorCard();
        Card debitorCard = accountCardMoneyContainer.getDebitorCard();
        BigDecimal outgoingMoney = accountCardMoneyContainer.getOutgoingMoney();

//        if (creditorCard.getActive() == 0 || debitorCard.getActive() == 0) {
//            throw BaseException.of(ErrorEnum.CARD_DEACTIVATED, HttpStatus.FORBIDDEN.value());
//        }
//
//        if (creditorCard.getBalance().compareTo(transferMoneyRequest.getOutgoingMoney()) < 0) {
//            throw BaseException.of(ErrorEnum.NOT_ENOUGH_BALANCE,HttpStatus.UNPROCESSABLE_ENTITY.value());
//        }
//
//        if (creditorCard.getStatus()== CardStatusEnum.BLCOK || debitorCard.getStatus()== CardStatusEnum.BLCOK){
//            throw BaseException.of(ErrorEnum.ACCOUNT_BLOCKED,HttpStatus.UNPROCESSABLE_ENTITY.value());
//        }




        if (creditorCard.getCurrency() == debitorCard.getCurrency()) {


            creditorCard.setBalance(creditorCard.getBalance().subtract(outgoingMoney));
            debitorCard.setBalance(debitorCard.getBalance().add(outgoingMoney));

            saveAllForCard(creditorCard, debitorCard);


        }

        else if (creditorCard.getCurrency() != debitorCard.getCurrency()) {

            if (creditorCard.getCurrency() == CurrencyEnum.AZN) {

                if (debitorCard.getCurrency() == CurrencyEnum.USD) {
                    BigDecimal currentCurrency = CurrencyScanner.getCurrency("USD");
                    BigDecimal outgoingMoneyAZN = outgoingMoney; //azn

                    BigDecimal outgoingMoneyUSD = outgoingMoney
                            .divide(currentCurrency, MathContext.DECIMAL32);


                    creditorCard.setBalance(creditorCard.getBalance().subtract(outgoingMoneyAZN));
                    debitorCard.setBalance(debitorCard.getBalance().add(outgoingMoneyUSD));

                    saveAllForCard(creditorCard, debitorCard);

                }

            }
            else if (creditorCard.getCurrency() == CurrencyEnum.USD) {

                if (debitorCard.getCurrency() == CurrencyEnum.AZN) {

                    BigDecimal currentCurrency = new BigDecimal("0.5883");
                    BigDecimal outgoingMoneyUSD = outgoingMoney; //azn

                    BigDecimal outgoingMoneyAZN = outgoingMoney
                            .divide(currentCurrency, MathContext.DECIMAL32);


                    creditorCard.setBalance(creditorCard.getBalance().subtract(outgoingMoneyUSD));
                    debitorCard.setBalance(debitorCard.getBalance().add(outgoingMoneyAZN));

                    saveAllForCard(creditorCard, debitorCard);
                }

            }


        }


        return convertToTransferResponse( creditorCard, debitorCard);
    }
    private void saveAllForCard(Card creditorCard, Card debitorCard) {

        cardRepository.save(creditorCard);
        cardRepository.save(debitorCard);

        Account creditorAccount = creditorCard.getAccount();
        creditorAccount.setBalance(creditorCard.getBalance());
        Account debitorAccount = debitorCard.getAccount();
        debitorAccount.setBalance(debitorCard.getBalance());


        creditorAccount.setBalance(creditorCard.getBalance());
        debitorAccount.setBalance(debitorCard.getBalance());

        accountRepository.save(creditorAccount);
        accountRepository.save(debitorAccount);
    }


    private TransferResponse convertToTransferResponse(Card creditorCard, Card debitorCard) {

        TransferResponse transferResponse = new TransferResponse();


        transferResponse.setTransferType(TransferTypeEnum.CARD_TO_CARD)
                .setCreditorCardID(creditorCard.getId())
                .setCreditorAccountID(creditorCard.getAccount().getId())
                .setCreditorCurrency(creditorCard.getCurrency())
                .setCreditorAmount(creditorCard.getBalance())

                .setDebitorCardID(debitorCard.getId())
                .setDebitorAccountID(debitorCard.getAccount().getId())
                .setDebitorCurrency(debitorCard.getCurrency())
                .setDebitorAmount(debitorCard.getBalance())


                .setStatus(TransferStatusEnum.SUCCESS);


        Transfer transferm = TransferMapper.MAPPER.toTransfer(transferResponse);

        Transfer savedTransfer = transferRepository.save(transferm);

        transferResponse.setId(savedTransfer.getId());


        return transferResponse;
    }
}

package com.example.bpmn_task.service.transferMoneyStrategies;

import com.example.bpmn_task.dto.request.TransferMoneyRequest;
import com.example.bpmn_task.dto.response.AccountCardMoneyContainer;
import com.example.bpmn_task.dto.response.TransferResponse;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.entity.Card;
import com.example.bpmn_task.entity.Transfer;
import com.example.bpmn_task.enums.*;
import com.example.bpmn_task.exception.BaseException;
import com.example.bpmn_task.mapper.TransferMapper;
import com.example.bpmn_task.repository.AccountRepository;
import com.example.bpmn_task.repository.CardRepository;
import com.example.bpmn_task.repository.TransferRepository;
import com.example.bpmn_task.utils.CurrencyScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;


@Component
public class TransferMoneyCardToAccountStrategy implements TransferMoneyStrategy{
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;
    private final TransferRepository transferRepository;

    @Autowired
    public TransferMoneyCardToAccountStrategy(AccountRepository accountRepository, CardRepository cardRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
        this.transferRepository = transferRepository;
    }

    @Override
    public TransferResponse transfer(AccountCardMoneyContainer accountCardMoneyContainer) {

        Card creditorCard = accountCardMoneyContainer.getCreditorCard();
        Account debitorAccount = accountCardMoneyContainer.getDebitorAccount();
        BigDecimal outgoingMoney = accountCardMoneyContainer.getOutgoingMoney();

//        if (creditorCard.getBalance().compareTo(transferMoneyRequest.getOutgoingMoney())<0){
//            throw BaseException.of(ErrorEnum.NOT_ENOUGH_BALANCE, HttpStatus.UNPROCESSABLE_ENTITY.value());
//        }
//
//        if (creditorCard.getActive()==0){
//            throw BaseException.of(ErrorEnum.ACCOUNT_DEACTIVATED,HttpStatus.FORBIDDEN.value());
//        }
//        else if (debitorAccount.getActive()==0){
//            throw BaseException.of(ErrorEnum.CARD_DEACTIVATED,HttpStatus.FORBIDDEN.value());
//        }
//
//        if (debitorAccount.getStatus()== AccountStatusEnum.BLOCK){
//            throw BaseException.of(ErrorEnum.ACCOUNT_BLOCKED,HttpStatus.UNPROCESSABLE_ENTITY.value());
//        }
//
//        if (creditorCard.getStatus()== CardStatusEnum.BLCOK){
//            throw BaseException.of(ErrorEnum.CARD_BLOCKED,HttpStatus.UNPROCESSABLE_ENTITY.value());
//        }


        if (creditorCard.getCurrency()==debitorAccount.getCurrency()){



            creditorCard.setBalance(creditorCard.getBalance().subtract(outgoingMoney));
            debitorAccount.setBalance(debitorAccount.getBalance().add(outgoingMoney));

            saveAllChanging( creditorCard,debitorAccount);

        }

        else{

            if (creditorCard.getCurrency()== CurrencyEnum.AZN){

                if (debitorAccount.getCurrency()==CurrencyEnum.USD){

                    BigDecimal currentCurrency = CurrencyScanner.getCurrency("USD");
                    BigDecimal outgoingMoneyAZN = outgoingMoney;

                    BigDecimal outgoingMoneyUSD = outgoingMoney
                            .divide(currentCurrency, MathContext.DECIMAL32);

                    creditorCard.setBalance(creditorCard.getBalance().subtract(outgoingMoneyAZN));
                    debitorAccount.setBalance(debitorAccount.getBalance().add(outgoingMoneyUSD));

                    saveAllChanging(creditorCard,debitorAccount);
                }

            }

            else if (creditorCard.getCurrency()==CurrencyEnum.USD){

                if (debitorAccount.getCurrency()==CurrencyEnum.AZN){

                    BigDecimal currentCurrency = new BigDecimal("0.5883");
                    BigDecimal outgoingMoneyUSD =outgoingMoney;//azn

                    BigDecimal outgoingMoneyAZN = outgoingMoney
                            .divide(currentCurrency, MathContext.DECIMAL32);


                    creditorCard.setBalance(creditorCard.getBalance().subtract(outgoingMoneyUSD));
                    debitorAccount.setBalance(debitorAccount.getBalance().add(outgoingMoneyAZN));

                    saveAllChanging(creditorCard,debitorAccount);
                }

            }



        }


        return convertToTransferResponse(debitorAccount,creditorCard);
    }

    private void saveAllChanging(Card creditorCard ,Account debitorAccount) {


        Account accountOfdebitorCard = creditorCard.getAccount();
        accountOfdebitorCard.setBalance(creditorCard.getBalance());
        accountOfdebitorCard.getCardList().forEach(
                card -> {
                    card.setBalance(accountOfdebitorCard.getBalance());
                }
        );


        debitorAccount.getCardList().forEach(
                card -> {
                    card.setBalance(debitorAccount.getBalance());
                }
        );


        cardRepository.save(creditorCard);
        accountRepository.save(debitorAccount);

    }


    private TransferResponse convertToTransferResponse(  Account debitorAccount, Card creditorCard) {

        TransferResponse transferResponse = new TransferResponse();


        transferResponse.setTransferType(TransferTypeEnum.CARD_TO_ACCOUNT)
                .setCreditorCardID(creditorCard.getId())
                .setCreditorCurrency(creditorCard.getCurrency())
                .setCreditorAmount(creditorCard.getBalance())


                .setDebitorAccountID(debitorAccount.getId())
                .setDebitorCurrency(debitorAccount.getCurrency())
                .setDebitorAmount(debitorAccount.getBalance())


                .setStatus(TransferStatusEnum.SUCCESS);


        Transfer transferm = TransferMapper.MAPPER.toTransfer(transferResponse);

        Transfer savedTransfer = transferRepository.save(transferm);
        transferResponse.setId(savedTransfer.getId());

        return transferResponse;
    }

}

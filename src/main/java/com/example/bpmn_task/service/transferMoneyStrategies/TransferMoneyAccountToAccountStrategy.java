package com.example.bpmn_task.service.transferMoneyStrategies;

import com.example.bpmn_task.dto.request.TransferMoneyRequest;
import com.example.bpmn_task.dto.response.AccountCardMoneyContainer;
import com.example.bpmn_task.dto.response.TransferResponse;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.entity.Transfer;
import com.example.bpmn_task.enums.*;
import com.example.bpmn_task.exception.BaseException;
import com.example.bpmn_task.mapper.TransferMapper;
import com.example.bpmn_task.repository.AccountRepository;
import com.example.bpmn_task.repository.TransferRepository;
import com.example.bpmn_task.service.functional.AccountFunctional;
import com.example.bpmn_task.utils.CurrencyScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;

@Component
public class TransferMoneyAccountToAccountStrategy implements TransferMoneyStrategy {


    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    @Autowired
    public TransferMoneyAccountToAccountStrategy(AccountRepository accountRepository, TransferRepository transferRepository) {

        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    @Transactional
    @Override
    public TransferResponse transfer(AccountCardMoneyContainer accountCardMoneyContainer) {


        Account creditorAccount = accountCardMoneyContainer.getCreditorAccount();
        Account debitorAccount = accountCardMoneyContainer.getDebitorAccount();
        BigDecimal outgoingMoney = accountCardMoneyContainer.getOutgoingMoney();


        if (creditorAccount.getCurrency() == debitorAccount.getCurrency()) {

            creditorAccount.setBalance(creditorAccount.getBalance().subtract(outgoingMoney));
            debitorAccount.setBalance(debitorAccount.getBalance().add(outgoingMoney));

            saveAllChanging(creditorAccount, debitorAccount);
        }

        if (creditorAccount.getCurrency() != debitorAccount.getCurrency()) {

            if (creditorAccount.getCurrency() == CurrencyEnum.AZN) {

                if (debitorAccount.getCurrency() == CurrencyEnum.USD) {
                    BigDecimal currentCurrency = CurrencyScanner.getCurrency("USD");
                    BigDecimal outgoingMoneyAZN = outgoingMoney; //azn

                    BigDecimal outgoingMoneyUSD = outgoingMoney
                            .divide(currentCurrency, MathContext.DECIMAL32);


                    creditorAccount.setBalance(creditorAccount.getBalance().subtract(outgoingMoneyAZN));
                    debitorAccount.setBalance(debitorAccount.getBalance().add(outgoingMoneyUSD));

                    saveAllChanging(creditorAccount, debitorAccount);

                }

            }


            if (creditorAccount.getCurrency() == CurrencyEnum.USD) {

                if (debitorAccount.getCurrency() == CurrencyEnum.AZN) {

                    BigDecimal currentCurrency = new BigDecimal("0.5883");
                    BigDecimal outgoingMoneyUSD = outgoingMoney; //azn

                    BigDecimal outgoingMoneyAZN = outgoingMoney
                            .divide(currentCurrency, MathContext.DECIMAL32);


                    creditorAccount.setBalance(creditorAccount.getBalance().subtract(outgoingMoneyUSD));
                    debitorAccount.setBalance(debitorAccount.getBalance().add(outgoingMoneyAZN));

                    saveAllChanging(creditorAccount, debitorAccount);
                }

            }


        }


        return convertToTransferResponse(creditorAccount, debitorAccount);


    }

    private void saveAllChanging(Account creditorAccount, Account debitorAccount) {

        Account savedCreditorAccount = accountRepository.save(creditorAccount);
        Account savedDebitorAccount = accountRepository.save(debitorAccount);


        savedCreditorAccount.getCardList().forEach(
                card -> card.setBalance(savedCreditorAccount.getBalance())

        );

        savedDebitorAccount.getCardList().forEach(
                card -> card.setBalance(savedDebitorAccount.getBalance())
        );


        accountRepository.save(creditorAccount);
        accountRepository.save(debitorAccount);

    }

    private TransferResponse convertToTransferResponse(Account creditorAccount, Account debitorAccount) {

        TransferResponse transferResponse = new TransferResponse();


        transferResponse.setTransferType(TransferTypeEnum.ACCOUNT_TO_ACCOUNT)
                .setCreditorAccountID(creditorAccount.getId())
                .setCreditorCurrency(creditorAccount.getCurrency())
                .setCreditorAmount(creditorAccount.getBalance())


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

package com.example.bpmn_task.service;


import com.example.bpmn_task.dto.request.TransferMoneyRequest;
import com.example.bpmn_task.dto.response.AccountCardMoneyContainer;
import com.example.bpmn_task.dto.response.TransferResponse;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.entity.Card;
import com.example.bpmn_task.service.transferMoneyStrategies.TransferMoneyAccountToAccountStrategy;
import com.example.bpmn_task.service.transferMoneyStrategies.TransferMoneyAccountToCardStrategy;
import com.example.bpmn_task.service.transferMoneyStrategies.TransferMoneyCardToAccountStrategy;
import com.example.bpmn_task.service.transferMoneyStrategies.TransferMoneyCardToCardStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class TransferMoneyServiceImpl implements TransferMoneyService {


    private final TransferMoneyCardToCardStrategy transferMoneyCardToCardStrategy;
    private final TransferMoneyAccountToAccountStrategy transferMoneyAccountToAccountStrategy;
    private final TransferMoneyAccountToCardStrategy transferMoneyAccountToCardStrategy;
    private final TransferMoneyCardToAccountStrategy transferMoneyCardToAccountStrategy;

    @Autowired
    public TransferMoneyServiceImpl(TransferMoneyCardToCardStrategy transferMoneyCartToCart, TransferMoneyAccountToAccountStrategy transferMoneyAccountToAccount, TransferMoneyAccountToCardStrategy transferMoneyAccountToCardStrategy, TransferMoneyCardToAccountStrategy transferMoneyCardToAccountStrategy) {
        this.transferMoneyCardToCardStrategy = transferMoneyCartToCart;
        this.transferMoneyAccountToAccountStrategy = transferMoneyAccountToAccount;
        this.transferMoneyAccountToCardStrategy = transferMoneyAccountToCardStrategy;
        this.transferMoneyCardToAccountStrategy = transferMoneyCardToAccountStrategy;
    }

    @Override
    public TransferResponse transferMoneyCardToCard(AccountCardMoneyContainer accountCardMoneyContainer) {
        return transferMoneyCardToCardStrategy.transfer(accountCardMoneyContainer);
    }

    @Override
    public TransferResponse transferMoneyAccountToAccount(AccountCardMoneyContainer accountCardMoneyContainer) {
        return transferMoneyAccountToAccountStrategy.transfer(accountCardMoneyContainer);
    }

    @Override
    public TransferResponse transferMoneyAccountToCard(AccountCardMoneyContainer accountCardMoneyContainer) {
        return transferMoneyAccountToCardStrategy.transfer(accountCardMoneyContainer);
    }

    @Override
    public TransferResponse transferMoneyCardToAccount(AccountCardMoneyContainer accountCardMoneyContainer) {
        return transferMoneyCardToAccountStrategy.transfer(accountCardMoneyContainer);
    }


}

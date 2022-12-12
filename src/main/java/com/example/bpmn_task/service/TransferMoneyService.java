package com.example.bpmn_task.service;


import com.example.bpmn_task.dto.request.TransferMoneyRequest;
import com.example.bpmn_task.dto.response.AccountCardMoneyContainer;
import com.example.bpmn_task.dto.response.TransferResponse;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.entity.Card;

import java.math.BigDecimal;

public interface TransferMoneyService {
    TransferResponse transferMoneyCardToCard(AccountCardMoneyContainer accountCardMoneyContainer);
    TransferResponse transferMoneyAccountToAccount(AccountCardMoneyContainer accountCardMoneyContainer);
    TransferResponse transferMoneyAccountToCard(AccountCardMoneyContainer accountCardMoneyContainer);
    TransferResponse transferMoneyCardToAccount(AccountCardMoneyContainer accountCardMoneyContainer);
}

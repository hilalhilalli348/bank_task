package com.example.bpmn_task.service.transferMoneyStrategies;

import com.example.bpmn_task.dto.request.TransferMoneyRequest;
import com.example.bpmn_task.dto.response.AccountCardMoneyContainer;
import com.example.bpmn_task.dto.response.TransferResponse;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.entity.Card;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public interface TransferMoneyStrategy {
   TransferResponse transfer(AccountCardMoneyContainer accountCardMoneyContainer) throws NoSuchAlgorithmException, KeyManagementException;

}

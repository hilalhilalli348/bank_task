package com.example.bpmn_task.dto.response;


import com.example.bpmn_task.enums.CurrencyEnum;
import com.example.bpmn_task.enums.TransferTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;


public class TransferMoneyResponse implements Serializable {
    private Long creditorAccountNumber;
    private Long creditorCardNumber;
    private CurrencyEnum creditorCurrency;

    private Long debitorAccountNumber;
    private Long debitorCardNumber;
    private CurrencyEnum debitorCurrency;

    private TransferTypeEnum transferType;

    private BigDecimal outgoingMoney;

    public TransferMoneyResponse() {
    }

    public TransferMoneyResponse(Long creditorAccountNumber, Long creditorCardNumber, CurrencyEnum creditorCurrency, Long debitorAccountNumber, Long debitorCardNumber, CurrencyEnum debitorCurrency, TransferTypeEnum transferType, BigDecimal outgoingMoney) {
        this.creditorAccountNumber = creditorAccountNumber;
        this.creditorCardNumber = creditorCardNumber;
        this.creditorCurrency = creditorCurrency;
        this.debitorAccountNumber = debitorAccountNumber;
        this.debitorCardNumber = debitorCardNumber;
        this.debitorCurrency = debitorCurrency;
        this.transferType = transferType;
        this.outgoingMoney = outgoingMoney;
    }

    public Long getCreditorAccountNumber() {
        return creditorAccountNumber;
    }

    public TransferMoneyResponse setCreditorAccountNumber(Long creditorAccountNumber) {
        this.creditorAccountNumber = creditorAccountNumber;
        return this;
    }

    public Long getCreditorCardNumber() {
        return creditorCardNumber;
    }

    public TransferMoneyResponse setCreditorCardNumber(Long creditorCardNumber) {
        this.creditorCardNumber = creditorCardNumber;
        return this;
    }

    public CurrencyEnum getCreditorCurrency() {
        return creditorCurrency;
    }

    public TransferMoneyResponse setCreditorCurrency(CurrencyEnum creditorCurrency) {
        this.creditorCurrency = creditorCurrency;
        return this;
    }

    public Long getDebitorAccountNumber() {
        return debitorAccountNumber;
    }

    public TransferMoneyResponse setDebitorAccountNumber(Long debitorAccountNumber) {
        this.debitorAccountNumber = debitorAccountNumber;
        return this;
    }

    public Long getDebitorCardNumber() {
        return debitorCardNumber;
    }

    public TransferMoneyResponse setDebitorCardNumber(Long debitorCardNumber) {
        this.debitorCardNumber = debitorCardNumber;
        return this;
    }

    public CurrencyEnum getDebitorCurrency() {
        return debitorCurrency;
    }

    public TransferMoneyResponse setDebitorCurrency(CurrencyEnum debitorCurrency) {
        this.debitorCurrency = debitorCurrency;
        return this;
    }

    public TransferTypeEnum getTransferType() {
        return transferType;
    }

    public TransferMoneyResponse setTransferType(TransferTypeEnum transferType) {
        this.transferType = transferType;
        return this;
    }

    public BigDecimal getOutgoingMoney() {
        return outgoingMoney;
    }

    public TransferMoneyResponse setOutgoingMoney(BigDecimal outgoingMoney) {
        this.outgoingMoney = outgoingMoney;
        return this;
    }

    @Override
    public String toString() {
        return "TransferMoneyResponse{" +
                "creditorAccountNumber=" + creditorAccountNumber +
                ", creditorCardNumber=" + creditorCardNumber +
                ", creditorCurrency=" + creditorCurrency +
                ", debitorAccountNumber=" + debitorAccountNumber +
                ", debitorCardNumber=" + debitorCardNumber +
                ", debitorCurrency=" + debitorCurrency +
                ", transferType=" + transferType +
                ", outgoingMoney=" + outgoingMoney +
                '}';
    }
}

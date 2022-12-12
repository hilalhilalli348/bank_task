package com.example.bpmn_task.dto.request;


import com.example.bpmn_task.enums.TransferTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransferMoneyRequest implements Serializable {

    private String creditorAccountNumber;
    private Long creditorCardNumber;
    private String debitorAccountNumber;
    private Long debitorCardNumber;
    private TransferTypeEnum transferType;

    private BigDecimal outgoingMoney;

    public TransferMoneyRequest() {
    }

    public TransferMoneyRequest(String creditorAccountNumber, Long creditorCardNumber, String debitorAccountNumber, Long debitorCardNumber, TransferTypeEnum transferType, BigDecimal outgoingMoney) {
        this.creditorAccountNumber = creditorAccountNumber;
        this.creditorCardNumber = creditorCardNumber;
        this.debitorAccountNumber = debitorAccountNumber;
        this.debitorCardNumber = debitorCardNumber;
        this.transferType = transferType;
        this.outgoingMoney = outgoingMoney;
    }

    public String getCreditorAccountNumber() {
        return creditorAccountNumber;
    }

    public TransferMoneyRequest setCreditorAccountNumber(String creditorAccountNumber) {
        this.creditorAccountNumber = creditorAccountNumber;
        return this;
    }

    public Long getCreditorCardNumber() {
        return creditorCardNumber;
    }

    public TransferMoneyRequest setCreditorCardNumber(Long creditorCardNumber) {
        this.creditorCardNumber = creditorCardNumber;
        return this;
    }

    public String getDebitorAccountNumber() {
        return debitorAccountNumber;
    }

    public TransferMoneyRequest setDebitorAccountNumber(String debitorAccountNumber) {
        this.debitorAccountNumber = debitorAccountNumber;
        return this;
    }

    public Long getDebitorCardNumber() {
        return debitorCardNumber;
    }

    public TransferMoneyRequest setDebitorCardNumber(Long debitorCardNumber) {
        this.debitorCardNumber = debitorCardNumber;
        return this;
    }

    public TransferTypeEnum getTransferType() {
        return transferType;
    }

    public TransferMoneyRequest setTransferType(TransferTypeEnum transferType) {
        this.transferType = transferType;
        return this;
    }

    public BigDecimal getOutgoingMoney() {
        return outgoingMoney;
    }

    public TransferMoneyRequest setOutgoingMoney(BigDecimal outgoingMoney) {
        this.outgoingMoney = outgoingMoney;
        return this;
    }

    @Override
    public String toString() {
        return "TransferMoneyRequest{" +
                "creditorAccountNumber='" + creditorAccountNumber + '\'' +
                ", creditorCardNumber=" + creditorCardNumber +
                ", debitorAccountNumber='" + debitorAccountNumber + '\'' +
                ", debitorCardNumber=" + debitorCardNumber +
                ", transferType=" + transferType +
                ", outgoingMoney=" + outgoingMoney +
                '}';
    }
}

package com.example.bpmn_task.dto.request;


import com.example.bpmn_task.enums.CurrencyEnum;
import com.example.bpmn_task.enums.TransferStatusEnum;
import com.example.bpmn_task.enums.TransferTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransferRequest implements Serializable {

    private Long creditorAccountID;
    private Long creditorCardID;
    private CurrencyEnum creditorCurrency;
    private BigDecimal creditorAmount;

    private Long debitorAccountID;
    private Long debitorCardID;
    private CurrencyEnum debitorCurrency;
    private BigDecimal debitorAmount;

    private TransferTypeEnum transferType;
    private TransferStatusEnum status;

    public TransferRequest() {
    }

    public TransferRequest(Long creditorAccountID, Long creditorCardID, CurrencyEnum creditorCurrency, BigDecimal creditorAmount, Long debitorAccountID, Long debitorCardID, CurrencyEnum debitorCurrency, BigDecimal debitorAmount, TransferTypeEnum transferType, TransferStatusEnum status) {
        this.creditorAccountID = creditorAccountID;
        this.creditorCardID = creditorCardID;
        this.creditorCurrency = creditorCurrency;
        this.creditorAmount = creditorAmount;
        this.debitorAccountID = debitorAccountID;
        this.debitorCardID = debitorCardID;
        this.debitorCurrency = debitorCurrency;
        this.debitorAmount = debitorAmount;
        this.transferType = transferType;
        this.status = status;
    }

    public Long getCreditorAccountID() {
        return creditorAccountID;
    }

    public TransferRequest setCreditorAccountID(Long creditorAccountID) {
        this.creditorAccountID = creditorAccountID;
        return this;
    }

    public Long getCreditorCardID() {
        return creditorCardID;
    }

    public TransferRequest setCreditorCardID(Long creditorCardID) {
        this.creditorCardID = creditorCardID;
        return this;
    }

    public CurrencyEnum getCreditorCurrency() {
        return creditorCurrency;
    }

    public TransferRequest setCreditorCurrency(CurrencyEnum creditorCurrency) {
        this.creditorCurrency = creditorCurrency;
        return this;
    }

    public BigDecimal getCreditorAmount() {
        return creditorAmount;
    }

    public TransferRequest setCreditorAmount(BigDecimal creditorAmount) {
        this.creditorAmount = creditorAmount;
        return this;
    }

    public Long getDebitorAccountID() {
        return debitorAccountID;
    }

    public TransferRequest setDebitorAccountID(Long debitorAccountID) {
        this.debitorAccountID = debitorAccountID;
        return this;
    }

    public Long getDebitorCardID() {
        return debitorCardID;
    }

    public TransferRequest setDebitorCardID(Long debitorCardID) {
        this.debitorCardID = debitorCardID;
        return this;
    }

    public CurrencyEnum getDebitorCurrency() {
        return debitorCurrency;
    }

    public TransferRequest setDebitorCurrency(CurrencyEnum debitorCurrency) {
        this.debitorCurrency = debitorCurrency;
        return this;
    }

    public BigDecimal getDebitorAmount() {
        return debitorAmount;
    }

    public TransferRequest setDebitorAmount(BigDecimal debitorAmount) {
        this.debitorAmount = debitorAmount;
        return this;
    }

    public TransferTypeEnum getTransferType() {
        return transferType;
    }

    public TransferRequest setTransferType(TransferTypeEnum transferType) {
        this.transferType = transferType;
        return this;
    }

    public TransferStatusEnum getStatus() {
        return status;
    }

    public TransferRequest setStatus(TransferStatusEnum status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "creditorAccountID=" + creditorAccountID +
                ", creditorCardID=" + creditorCardID +
                ", creditorCurrency=" + creditorCurrency +
                ", creditorAmount=" + creditorAmount +
                ", debitorAccountID=" + debitorAccountID +
                ", debitorCardID=" + debitorCardID +
                ", debitorCurrency=" + debitorCurrency +
                ", debitorAmount=" + debitorAmount +
                ", transferType=" + transferType +
                ", status=" + status +
                '}';
    }
}

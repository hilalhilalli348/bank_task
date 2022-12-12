package com.example.bpmn_task.dto.response;

import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.entity.Card;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountCardMoneyContainer implements Serializable {
    private Account creditorAccount;
    private Account debitorAccount;
    private Card creditorCard;
    private Card debitorCard;
    private BigDecimal outgoingMoney;

    public Account getCreditorAccount() {
        return creditorAccount;
    }

    public AccountCardMoneyContainer setCreditorAccount(Account creditorAccount) {
        this.creditorAccount = creditorAccount;
        return this;
    }

    public Account getDebitorAccount() {
        return debitorAccount;
    }

    public AccountCardMoneyContainer setDebitorAccount(Account debitorAccount) {
        this.debitorAccount = debitorAccount;
        return this;
    }

    public Card getCreditorCard() {
        return creditorCard;
    }

    public AccountCardMoneyContainer setCreditorCard(Card creditorCard) {
        this.creditorCard = creditorCard;
        return this;
    }

    public Card getDebitorCard() {
        return debitorCard;
    }

    public AccountCardMoneyContainer setDebitorCard(Card debitorCard) {
        this.debitorCard = debitorCard;
        return this;
    }

    public BigDecimal getOutgoingMoney() {
        return outgoingMoney;
    }

    public AccountCardMoneyContainer setOutgoingMoney(BigDecimal outgoingMoney) {
        this.outgoingMoney = outgoingMoney;
        return this;
    }
}

package com.example.bpmn_task.dto.request;



import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.enums.CardStatusEnum;
import com.example.bpmn_task.enums.CurrencyEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CardRequest implements Serializable {

    private Long cardNumber;
    private BigDecimal balance;
    private CurrencyEnum currency;
    private CardStatusEnum status;
    private LocalDateTime expireDate;
    private LocalDateTime createDate=LocalDateTime.now();

    private Integer active =1;
    private Account account;
    public CardRequest() {
    }

    public CardRequest(Long cardNumber, BigDecimal balance, CurrencyEnum currency, CardStatusEnum status, LocalDateTime expireDate, LocalDateTime createDate, Integer active, Account account) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.currency = currency;
        this.status = status;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.active = active;
        this.account = account;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public CardRequest setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public CardRequest setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public CardRequest setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    public CardStatusEnum getStatus() {
        return status;
    }

    public CardRequest setStatus(CardStatusEnum status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public CardRequest setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public CardRequest setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public Integer getActive() {
        return active;
    }

    public CardRequest setActive(Integer active) {
        this.active = active;
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public CardRequest setAccount(Account account) {
        this.account = account;
        return this;
    }

    @Override
    public String toString() {
        return "CardRequest{" +
                "cardNumber=" + cardNumber +
                ", balance=" + balance +
                ", currency=" + currency +
                ", status=" + status +
                ", expireDate=" + expireDate +
                ", createDate=" + createDate +
                ", active=" + active +
                ", account=" + account +
                '}';
    }
}

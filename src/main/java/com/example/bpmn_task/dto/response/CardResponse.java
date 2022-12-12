package com.example.bpmn_task.dto.response;



import com.example.bpmn_task.enums.CardStatusEnum;
import com.example.bpmn_task.enums.CurrencyEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CardResponse implements Serializable {
    private Long id;
    private Long cardNumber;
    private BigDecimal balance;
    private CurrencyEnum currency;
    private CardStatusEnum status;
    private LocalDateTime expireDate;
    private LocalDateTime createDate;
    private AccountResponse accountResponse;
    private Integer active;


    public CardResponse() {
    }

    public CardResponse(Long id, Long cardNumber, BigDecimal balance, CurrencyEnum currency, CardStatusEnum status, LocalDateTime expireDate, LocalDateTime createDate, AccountResponse accountResponse, Integer active) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.currency = currency;
        this.status = status;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.accountResponse = accountResponse;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public CardResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public CardResponse setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public CardResponse setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public CardResponse setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    public CardStatusEnum getStatus() {
        return status;
    }

    public CardResponse setStatus(CardStatusEnum status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public CardResponse setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public CardResponse setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public AccountResponse getAccountResponse() {
        return accountResponse;
    }

    public CardResponse setAccountResponse(AccountResponse accountResponse) {
        this.accountResponse = accountResponse;
        return this;
    }

    public Integer getActive() {
        return active;
    }

    public CardResponse setActive(Integer active) {
        this.active = active;
        return this;
    }

    @Override
    public String toString() {
        return "CardResponse{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", balance=" + balance +
                ", currency=" + currency +
                ", status=" + status +
                ", expireDate=" + expireDate +
                ", createDate=" + createDate +
                ", accountResponse=" + accountResponse +
                ", active=" + active +
                '}';
    }
}

package com.example.bpmn_task.dto.response;




import com.example.bpmn_task.enums.CurrencyEnum;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountResponse implements Serializable {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private CurrencyEnum currency;
    private String iban;

    private CustomerResponse customerResponse;

    public AccountResponse() {
    }

    public AccountResponse(Long id, String accountNumber, BigDecimal balance, CurrencyEnum currency, String iban, CustomerResponse customerResponse) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
        this.iban = iban;
        this.customerResponse = customerResponse;
    }

    public Long getId() {
        return id;
    }

    public AccountResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountResponse setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountResponse setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public AccountResponse setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    public String getIban() {
        return iban;
    }

    public AccountResponse setIban(String iban) {
        this.iban = iban;
        return this;
    }

    public CustomerResponse getCustomerResponse() {
        return customerResponse;
    }

    public AccountResponse setCustomerResponse(CustomerResponse customerResponse) {
        this.customerResponse = customerResponse;
        return this;
    }

    @Override
    public String toString() {
        return "AccountResponse{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                ", iban='" + iban + '\'' +
                ", customerResponse=" + customerResponse +
                '}';
    }
}

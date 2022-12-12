package com.example.bpmn_task.dto.request;




import com.example.bpmn_task.entity.Customer;
import com.example.bpmn_task.enums.CurrencyEnum;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountRequest implements Serializable {

    private String accountNumber;
    private BigDecimal balance;
    private CurrencyEnum currency;
    private String iban;

    private Customer customer;

    public AccountRequest() {
    }

    public AccountRequest(String accountNumber, BigDecimal balance, CurrencyEnum currency, String iban, Customer customer) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
        this.iban = iban;
        this.customer = customer;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountRequest setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountRequest setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public AccountRequest setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    public String getIban() {
        return iban;
    }

    public AccountRequest setIban(String iban) {
        this.iban = iban;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public AccountRequest setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    @Override
    public String toString() {
        return "AccountRequest{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                ", iban='" + iban + '\'' +
                ", customer=" + customer +
                '}';
    }
}

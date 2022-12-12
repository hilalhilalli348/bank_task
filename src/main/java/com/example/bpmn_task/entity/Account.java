package com.example.bpmn_task.entity;




import com.example.bpmn_task.enums.AccountStatusEnum;
import com.example.bpmn_task.enums.CurrencyEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "account",schema = "brteachway")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iban",length = 30)
    private String iban;

    @Column(name = "account_number",unique = true,length = 20)
    private String accountNumber;

    @Column(name = "balance",length = 12)
    private BigDecimal balance;

    @Column(name = "currency",length = 3)
    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;


    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private AccountStatusEnum status = AccountStatusEnum.ACTIVE;


    @Column(name = "create_date")
    private LocalDateTime createDate=LocalDateTime.now();

    @Column(name = "active")
    private Integer active=1;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",unique = false)
    private Customer customer;


    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private List<Card> cardList;

    public Account() {
    }

    public Account(Long id, String iban, String accountNumber, BigDecimal balance, CurrencyEnum currency, AccountStatusEnum status, LocalDateTime createDate, Integer active, Customer customer, List<Card> cardList) {
        this.id = id;
        this.iban = iban;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
        this.status = status;
        this.createDate = createDate;
        this.active = active;
        this.customer = customer;
        this.cardList = cardList;
    }

    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public String getIban() {
        return iban;
    }

    public Account setIban(String iban) {
        this.iban = iban;
        return this;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Account setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Account setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public Account setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    public AccountStatusEnum getStatus() {
        return status;
    }

    public Account setStatus(AccountStatusEnum status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public Account setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public Integer getActive() {
        return active;
    }

    public Account setActive(Integer active) {
        this.active = active;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Account setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public Account setCardList(List<Card> cardList) {
        this.cardList = cardList;
        return this;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                ", status=" + status +
                ", createDate=" + createDate +
                ", active=" + active +
                '}';
    }
}

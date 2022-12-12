package com.example.bpmn_task.entity;



import com.example.bpmn_task.enums.CurrencyEnum;
import com.example.bpmn_task.enums.TransferStatusEnum;
import com.example.bpmn_task.enums.TransferTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer",schema = "brteachway")
public class Transfer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "debitor_card_id")
    private Long debitorCardID;

    @Column(name = "creditor_card_id")
    private Long creditorCardID;

    @Column(name = "debitor_account_id")
    private Long debitorAccountID;

    @Column(name = "creditor_account_id")
    private Long creditorAccountID;

    @Column(name = "debitor_amount")
    private BigDecimal debitorAmount;

    @Column(name = "debitor_currency",length = 3)
    @Enumerated(EnumType.STRING)
    private CurrencyEnum debitorCurrency;

    @Column(name = "creditor_amount")
    private BigDecimal creditorAmount;

    @Column(name = "creditor_currency",length = 3)
    @Enumerated(EnumType.STRING)
    private CurrencyEnum creditorCurrency;

    @Column(name = "transfer_type")
    @Enumerated(EnumType.ORDINAL)
    private TransferTypeEnum transferType;


    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private TransferStatusEnum status;


    @Column(name = "active")
    private Integer active=1;

    @Column(name = "create_date")
    private LocalDateTime createDate=LocalDateTime.now();



    public Transfer() {
    }

    public Transfer(Long id, Long debitorCardID, Long creditorCardID, Long debitorAccountID, Long creditorAccountID, BigDecimal debitorAmount, CurrencyEnum debitorCurrency, BigDecimal creditorAmount, CurrencyEnum creditorCurrency, TransferTypeEnum transferType, TransferStatusEnum status, Integer active, LocalDateTime createDate) {
        this.id = id;
        this.debitorCardID = debitorCardID;
        this.creditorCardID = creditorCardID;
        this.debitorAccountID = debitorAccountID;
        this.creditorAccountID = creditorAccountID;
        this.debitorAmount = debitorAmount;
        this.debitorCurrency = debitorCurrency;
        this.creditorAmount = creditorAmount;
        this.creditorCurrency = creditorCurrency;
        this.transferType = transferType;
        this.status = status;
        this.active = active;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDebitorCardID() {
        return debitorCardID;
    }

    public void setDebitorCardID(Long debitorCardID) {
        this.debitorCardID = debitorCardID;
    }

    public Long getCreditorCardID() {
        return creditorCardID;
    }

    public void setCreditorCardID(Long creditorCardID) {
        this.creditorCardID = creditorCardID;
    }

    public Long getDebitorAccountID() {
        return debitorAccountID;
    }

    public void setDebitorAccountID(Long debitorAccountID) {
        this.debitorAccountID = debitorAccountID;
    }

    public Long getCreditorAccountID() {
        return creditorAccountID;
    }

    public void setCreditorAccountID(Long creditorAccountID) {
        this.creditorAccountID = creditorAccountID;
    }

    public BigDecimal getDebitorAmount() {
        return debitorAmount;
    }

    public void setDebitorAmount(BigDecimal debitorAmount) {
        this.debitorAmount = debitorAmount;
    }

    public CurrencyEnum getDebitorCurrency() {
        return debitorCurrency;
    }

    public void setDebitorCurrency(CurrencyEnum debitorCurrency) {
        this.debitorCurrency = debitorCurrency;
    }

    public BigDecimal getCreditorAmount() {
        return creditorAmount;
    }

    public void setCreditorAmount(BigDecimal creditorAmount) {
        this.creditorAmount = creditorAmount;
    }

    public CurrencyEnum getCreditorCurrency() {
        return creditorCurrency;
    }

    public void setCreditorCurrency(CurrencyEnum creditorCurrency) {
        this.creditorCurrency = creditorCurrency;
    }

    public TransferTypeEnum getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferTypeEnum transferType) {
        this.transferType = transferType;
    }

    public TransferStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TransferStatusEnum status) {
        this.status = status;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", debitorCardID=" + debitorCardID +
                ", creditorCardID=" + creditorCardID +
                ", debitorAccountID=" + debitorAccountID +
                ", creditorAccountID=" + creditorAccountID +
                ", debitorAmount=" + debitorAmount +
                ", debitorCurrency=" + debitorCurrency +
                ", creditorAmount=" + creditorAmount +
                ", creditorCurrency=" + creditorCurrency +
                ", transferType=" + transferType +
                ", status=" + status +
                ", active=" + active +
                ", createDate=" + createDate +
                '}';
    }
}

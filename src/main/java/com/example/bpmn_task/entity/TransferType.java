package com.example.bpmn_task.entity;




import com.example.bpmn_task.enums.TransferTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_type",schema = "brteachway")
public class TransferType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",length = 30)
    @Enumerated(EnumType.STRING)
    private TransferTypeEnum name;

    @Column(name = "create_date")
    private LocalDateTime createDate=LocalDateTime.now();

    @Column(name = "active")
    private Integer active = 1;

    public TransferType() {
    }

    public TransferType(Long id, TransferTypeEnum name, LocalDateTime createDate, Integer active) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransferTypeEnum getName() {
        return name;
    }

    public void setName(TransferTypeEnum name) {
        this.name = name;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "TransferType{" +
                "id=" + id +
                ", name=" + name +
                ", createDate=" + createDate +
                ", active=" + active +
                '}';
    }
}

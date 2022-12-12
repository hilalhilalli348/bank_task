package com.example.bpmn_task.entity;




import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_type",schema = "brteachway")
public class CustomerType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",length = 20)
    private String name;

    @Column(name = "create_date")
    private LocalDateTime createDate  = LocalDateTime.now();

    @Column(name = "active")
    private Integer active =1;

    public CustomerType() {
    }

    public CustomerType(Long id, String name, LocalDateTime createDate, Integer active) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
        return "CustomerType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", active=" + active +
                '}';
    }
}

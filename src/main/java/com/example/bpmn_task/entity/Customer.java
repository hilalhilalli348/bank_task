package com.example.bpmn_task.entity;



import com.example.bpmn_task.enums.CustomerTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer",schema = "brteachway")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",length = 20)
    private String name;

    @Column(name = "surname",length = 20)
    private String surname;

    @Column(name = "father_name",length = 20)
    private String fatherName;

    @Column(name = "fin",length = 7)
    private String fin;

    @Column(name = "voen",length = 7)
    private String voen;

    @Column(name = "age",length = 3)
    private Integer age;

    @Column(name = "address",length = 100)
    private String address;

    @Column(name = "create_date")
    private LocalDateTime createDate  = LocalDateTime.now();

    @Column(name = "active")
    private Integer active =1;

    @Column(name = "customer_type")
    @Enumerated(EnumType.ORDINAL)
    private CustomerTypeEnum customerType;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Account> accounts;

    public Customer() {
    }

    public Customer(Long id, String name, String surname, String fatherName, String fin, String voen, Integer age, String address, LocalDateTime createDate, Integer active, CustomerTypeEnum customerType, List<Account> accounts) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.fatherName = fatherName;
        this.fin = fin;
        this.voen = voen;
        this.age = age;
        this.address = address;
        this.createDate = createDate;
        this.active = active;
        this.customerType = customerType;
        this.accounts = accounts;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getVoen() {
        return voen;
    }

    public void setVoen(String voen) {
        this.voen = voen;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public CustomerTypeEnum getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerTypeEnum customerType) {
        this.customerType = customerType;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", fin='" + fin + '\'' +
                ", voen='" + voen + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", createDate=" + createDate +
                ", active=" + active +
                ", customerType=" + customerType +
                '}';
    }
}

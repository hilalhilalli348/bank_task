package com.example.bpmn_task.dto.request;




import com.example.bpmn_task.enums.CustomerTypeEnum;

import java.io.Serializable;


public class CustomerRequest implements Serializable {


    private String name;
    private String surname;
    private String fatherName;
    private String fin;
    private String voen;
    private Integer age;
    private String address;
    private CustomerTypeEnum customerType;

    public CustomerRequest() {
    }

    public CustomerRequest(String name, String surname, String fatherName, String fin, String voen, Integer age, String address, CustomerTypeEnum customerType) {
        this.name = name;
        this.surname = surname;
        this.fatherName = fatherName;
        this.fin = fin;
        this.voen = voen;
        this.age = age;
        this.address = address;
        this.customerType = customerType;
    }

    public String getName() {
        return name;
    }

    public CustomerRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public CustomerRequest setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getFatherName() {
        return fatherName;
    }

    public CustomerRequest setFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public String getFin() {
        return fin;
    }

    public CustomerRequest setFin(String fin) {
        this.fin = fin;
        return this;
    }

    public String getVoen() {
        return voen;
    }

    public CustomerRequest setVoen(String voen) {
        this.voen = voen;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public CustomerRequest setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CustomerRequest setAddress(String address) {
        this.address = address;
        return this;
    }

    public CustomerTypeEnum getCustomerType() {
        return customerType;
    }

    public CustomerRequest setCustomerType(CustomerTypeEnum customerType) {
        this.customerType = customerType;
        return this;
    }

    @Override
    public String toString() {
        return "CustomerRequest{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", fin='" + fin + '\'' +
                ", voen='" + voen + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", customerType=" + customerType +
                '}';
    }
}

package com.example.bpmn_task.dto.response;

import java.io.Serializable;

public class CustomerResponse implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String address;

    public CustomerResponse() {
    }

    public CustomerResponse(Long id, String name, String surname, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public CustomerResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomerResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public CustomerResponse setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public CustomerResponse setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CustomerResponse setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public String toString() {
        return "CustomerResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}

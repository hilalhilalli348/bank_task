package com.example.bpmn_task.service.functional;


import com.example.bpmn_task.entity.Customer;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.exception.BaseException;
import com.example.bpmn_task.repository.CustomerRepository;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerFunctional {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerFunctional(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findByID(Long id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() ->BaseException.of(ErrorEnum.CUSTOMER_NOT_FOUND,HttpStatus.NOT_FOUND.value()));
    }


}

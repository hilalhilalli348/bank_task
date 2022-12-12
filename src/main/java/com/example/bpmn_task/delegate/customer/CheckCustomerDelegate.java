package com.example.bpmn_task.delegate.customer;

import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Customer;

import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.repository.CustomerRepository;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component("checkCustomerDelegate")
public class CheckCustomerDelegate implements JavaDelegate {

    private final CustomerRepository customerRepository;

    @Autowired
    public CheckCustomerDelegate(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Long customerID = (Long) execution.getVariable("customerID");
        Customer customer =customerRepository.findById(customerID).orElse(null);


        execution.setVariable("customer",customer);
        execution.setVariable("status", Status.of(ErrorEnum.CUSTOMER_NOT_FOUND.getStatus(),ErrorEnum.CUSTOMER_NOT_FOUND.getMessage()));

    }

}

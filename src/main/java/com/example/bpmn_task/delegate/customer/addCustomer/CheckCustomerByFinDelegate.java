package com.example.bpmn_task.delegate.customer.addCustomer;

import com.example.bpmn_task.dto.request.CustomerRequest;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Customer;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.repository.CustomerRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("checkCustomerByFinDelegate")
public class CheckCustomerByFinDelegate implements JavaDelegate {
    private final CustomerRepository customerRepository;

    @Autowired
    public CheckCustomerByFinDelegate(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public void execute(DelegateExecution execution) throws Exception {

        CustomerRequest customerRequest = (CustomerRequest) execution.getVariable("customerRequest");
        String finCode = customerRequest.getFin();

        Customer customer = customerRepository.findByFin(finCode).orElse(null);


        execution.setVariable("customer", customer);
        execution.setVariable("status", Status.of(ErrorEnum.CUSTOMER_IS_EXIST.getStatus(), ErrorEnum.CUSTOMER_IS_EXIST.getMessage()));

    }
}

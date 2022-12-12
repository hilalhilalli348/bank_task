package com.example.bpmn_task.delegate.customer.removeCustomer;

import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.CustomerResponse;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Customer;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.mapper.CustomerMapper;
import com.example.bpmn_task.repository.CustomerRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("removeCustomerDelegate")
public class RemoveCustomerDelegate implements JavaDelegate {

    private final CustomerRepository customerRepository;

    @Autowired
    public RemoveCustomerDelegate(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void execute(DelegateExecution execution){

        Customer customer = (Customer) execution.getVariable("customer");

        customerRepository.delete(customer);

        CustomerResponse customerResponse = CustomerMapper.MAPPER.toCustomerResponse(customer);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(customerResponse);
        commonResponse.setStatus(Status.of(ErrorEnum.SUCCESS.getStatus(),ErrorEnum.SUCCESS.getMessage()));
        commonResponse.setHttpStatusCode(HttpStatus.OK.value());

        execution.setVariable("response",commonResponse);

    }
}

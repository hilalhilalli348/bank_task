package com.example.bpmn_task.delegate.customer.updateCustomer;

import com.example.bpmn_task.dto.request.CustomerRequest;
import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.CustomerResponse;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Customer;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.mapper.CustomerMapper;
import com.example.bpmn_task.service.CustomerService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("updateCustomerDelegate")
public class UpdateCustomerDelegate implements JavaDelegate {

    private final CustomerService customerService;

    @Autowired
    public UpdateCustomerDelegate(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void execute(DelegateExecution execution) {

        CustomerRequest customerRequest = (CustomerRequest) execution.getVariable("customerRequest");
        Customer customer = (Customer) execution.getVariable("customer");

        CustomerResponse customerResponse = customerService.updateCustomer(customer,customerRequest);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setHttpStatusCode(HttpStatus.OK.value());
        commonResponse.setStatus(Status.of(ErrorEnum.SUCCESS.getStatus(),ErrorEnum.SUCCESS.getMessage()));
        commonResponse.setData(customerResponse);

        execution.setVariable("response", commonResponse);
    }
}

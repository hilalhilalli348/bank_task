package com.example.bpmn_task.delegate.customer.getCustomer;

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

@Component("getCustomerDelegate")
public class GetCustomerDelegate implements JavaDelegate {

    private final CustomerService customerService;

    @Autowired
    public GetCustomerDelegate(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void execute(DelegateExecution execution) {


        Customer customer = (Customer) execution.getVariable("customer");
        CustomerResponse customerResponse = CustomerMapper.MAPPER.toCustomerResponse(customer);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(customerResponse);
        commonResponse.setStatus(Status.of(ErrorEnum.SUCCESS.getStatus(),ErrorEnum.SUCCESS.getMessage()));
        commonResponse.setHttpStatusCode(HttpStatus.OK.value());

        execution.setVariable("response", commonResponse);

    }
}

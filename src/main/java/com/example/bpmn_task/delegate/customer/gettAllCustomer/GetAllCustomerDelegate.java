package com.example.bpmn_task.delegate.customer.gettAllCustomer;

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

import java.util.List;

@Component("getAllCustomerDelegate")
public class GetAllCustomerDelegate implements JavaDelegate {
    private final CustomerService customerService;

    @Autowired
    public GetAllCustomerDelegate(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Override
    public void execute(DelegateExecution execution) {

        List<CustomerResponse> customerResponseList = customerService.getAllCustomer();

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(customerResponseList);
        commonResponse.setStatus(Status.of(ErrorEnum.SUCCESS.getStatus(),ErrorEnum.SUCCESS.getMessage()));
        commonResponse.setHttpStatusCode(HttpStatus.OK.value());

        execution.setVariable("response", commonResponse);
    }
}

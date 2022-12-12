package com.example.bpmn_task.service;

import com.example.bpmn_task.dto.request.CustomerRequest;
import com.example.bpmn_task.dto.response.AccountResponse;
import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.CustomerResponse;
import com.example.bpmn_task.entity.Customer;

import java.util.List;
import java.util.Optional;


public interface CustomerService {
    List<CustomerResponse> getAllCustomer();
    CustomerResponse updateCustomer(Customer customer,CustomerRequest customerRequest);
    CustomerResponse addCustomer(CustomerRequest customerRequest);
}

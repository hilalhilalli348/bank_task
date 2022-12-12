package com.example.bpmn_task.mapper;


import com.example.bpmn_task.dto.request.CustomerRequest;
import com.example.bpmn_task.dto.response.CustomerResponse;
import com.example.bpmn_task.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper{
    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    CustomerResponse toCustomerResponse(Customer customer);

    Customer toCustomer(CustomerRequest customerRequest);



    List<CustomerResponse> toCustomerResponseList(List<Customer> customers);
}

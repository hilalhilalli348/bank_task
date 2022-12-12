package com.example.bpmn_task.service;

import com.example.bpmn_task.dto.request.CustomerRequest;
import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.CustomerResponse;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Customer;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.exception.BaseException;
import com.example.bpmn_task.mapper.CustomerMapper;
import com.example.bpmn_task.repository.CustomerRepository;
import com.example.bpmn_task.repository.CustomerTypeRepository;

import com.example.bpmn_task.service.functional.CustomerFunctional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerTypeRepository customerTypeRepository;

    private final CustomerFunctional customerFunctional;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerTypeRepository customerTypeRepository, CustomerFunctional customerFunctional) {
        this.customerRepository = customerRepository;
        this.customerTypeRepository = customerTypeRepository;
        this.customerFunctional = customerFunctional;
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {

        List<Customer> customerList = customerRepository.findAll();
        List<CustomerResponse> customerResponseList = CustomerMapper.MAPPER.toCustomerResponseList(customerList);


        return customerResponseList;
    }



    @Override
    @Transactional
    public CustomerResponse updateCustomer(Customer customer,CustomerRequest customerRequest) {
        convertCustomerRequestToCustomer(customerRequest,customer);
        Customer updatedCard = customerRepository.save(customer);
        CustomerResponse customerResponse = CustomerMapper.MAPPER.toCustomerResponse(updatedCard);
        return customerResponse;
    }

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        Customer customer = CustomerMapper.MAPPER.toCustomer(customerRequest);
        Customer savedCustomer =  customerRepository.save(customer);
        CustomerResponse customerResponse = CustomerMapper.MAPPER.toCustomerResponse(savedCustomer);

        return customerResponse;
    }





    private Customer convertCustomerRequestToCustomer(CustomerRequest customerRequest, Customer customer) {
        if (customerRequest == null || customer == null) {
            return null;
        }
        customer.setCustomerType(
                customerRequest.getCustomerType() != null
                        ? customerRequest.getCustomerType() :
                        customer.getCustomerType()
        );


        customer.setAddress(
                customerRequest.getAddress() != null
                        ? customerRequest.getAddress() :
                        customer.getAddress()
        );




        customer.setAge(
                customerRequest.getAge() != null
                        ? customerRequest.getAge() :
                        customer.getAge()
        );





        customer.setName(
                customerRequest.getName() != null
                        ? customerRequest.getName() :
                        customer.getName()
        );


        customer.setFin(
                customerRequest.getFin() != null
                        ? customerRequest.getFin() :
                        customer.getFin()
        );


        customer.setSurname(
                customerRequest.getSurname() != null
                        ? customerRequest.getSurname() :
                        customer.getSurname()
        );


        customer.setFatherName(
                customerRequest.getFatherName() != null
                        ? customerRequest.getFatherName() :
                        customer.getFatherName()
        );


        return customer;
    }






}

package com.example.bpmn_task.controller;


import com.example.bpmn_task.dto.request.CustomerRequest;
import com.example.bpmn_task.dto.response.CommonResponse;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/customers")
public class CustomerController {


    private final RuntimeService runtimeService;

    @Autowired
    public CustomerController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<?> getCustomer(@PathVariable("id") Long id) {

        ProcessInstanceWithVariables processInstanceWithVariables = runtimeService.createProcessInstanceByKey("getCustomerProcess")
                .setVariable("isCustomerExist", true)
                .setVariable("customerID", id)
                .executeWithVariablesInReturn();

        CommonResponse response = processInstanceWithVariables.getVariables().getValue("response", CommonResponse.class);
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }


    @GetMapping
    public ResponseEntity<?> getAllCustomer() {

        ProcessInstanceWithVariables processInstanceWithVariables = runtimeService.createProcessInstanceByKey("getAllCustomerProcess")
                .executeWithVariablesInReturn();


        CommonResponse response = processInstanceWithVariables.getVariables().getValue("response", CommonResponse.class);
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody CustomerRequest customerRequest) {

        ProcessInstanceWithVariables processInstanceWithVariables =
                runtimeService.createProcessInstanceByKey("addCustomerProcess")
                        .setVariable("customerRequest", customerRequest)
                        .executeWithVariablesInReturn();

        CommonResponse response = processInstanceWithVariables.getVariables().getValue("response", CommonResponse.class);
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }


    @PatchMapping(path = "{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerRequest customerRequest, @PathVariable("id") Long id) {
        ProcessInstanceWithVariables processInstanceWithVariables = runtimeService.createProcessInstanceByKey("updateCustomerProcess")
                .setVariable("customerID", id)
                .setVariable("customerRequest", customerRequest)
                .executeWithVariablesInReturn();

        CommonResponse response = processInstanceWithVariables.getVariables().getValue("response", CommonResponse.class);
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }


    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {

        ProcessInstanceWithVariables processInstanceWithVariables =
                runtimeService.createProcessInstanceByKey("removeCustomerProcess")
                        .setVariable("customerID", id)
                        .setVariable("isCustomerExist", true)
                        .executeWithVariablesInReturn();


        CommonResponse response = processInstanceWithVariables.getVariables().getValue("response", CommonResponse.class);
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }


}

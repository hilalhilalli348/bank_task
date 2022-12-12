package com.example.bpmn_task.controller;

import com.example.bpmn_task.dto.request.AccountRequest;
import com.example.bpmn_task.dto.response.CommonResponse;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/accounts")
public class AccountController {

    private final RuntimeService runtimeService;

    @Autowired
    public AccountController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getAccount(@PathVariable("id") Long id){

        ProcessInstanceWithVariables accountProcess = runtimeService.createProcessInstanceByKey("getAccountProcess")
                .setVariable("accountID",id)
               .executeWithVariablesInReturn();

        CommonResponse response = accountProcess.getVariables().getValue("response", CommonResponse.class);

        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }


    @GetMapping
    public ResponseEntity<?> getAllAccount() {
        ProcessInstanceWithVariables accountProcess = runtimeService.createProcessInstanceByKey("getAllAccountProcess")
                .executeWithVariablesInReturn();


        CommonResponse response = accountProcess.getVariables().getValue("response", CommonResponse.class);

        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<?> addAccount(@RequestBody AccountRequest accountRequest) {

        ProcessInstanceWithVariables processInstanceWithVariables =
                runtimeService.createProcessInstanceByKey("addAccountProcess")
                        .setVariable("accountRequest", accountRequest)
                        .executeWithVariablesInReturn();


        CommonResponse response = processInstanceWithVariables.getVariables().getValue("response", CommonResponse.class);

        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<?> updateAccount(@RequestBody AccountRequest accountRequest,@PathVariable("id") Long id){
        ProcessInstanceWithVariables accountProcess = runtimeService.createProcessInstanceByKey("updateAccountProcess")
                .setVariable("accountRequest",accountRequest)
                .setVariable("accountID",id)
                .executeWithVariablesInReturn();


        CommonResponse response = accountProcess.getVariables().getValue("response", CommonResponse.class);

        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id){
        ProcessInstanceWithVariables accountProcess = runtimeService.createProcessInstanceByKey("removeAccountProcess")
                .setVariable("accountID",id)
                .executeWithVariablesInReturn();


        CommonResponse response = accountProcess.getVariables().getValue("response", CommonResponse.class);

        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }

}

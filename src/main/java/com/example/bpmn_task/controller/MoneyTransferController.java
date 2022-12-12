package com.example.bpmn_task.controller;

import com.example.bpmn_task.dto.request.TransferMoneyRequest;
import com.example.bpmn_task.dto.response.CommonResponse;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("v1/money-transfers")
public class MoneyTransferController {

    private final RuntimeService runtimeService;

    @Autowired
    public MoneyTransferController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @PostMapping
    public ResponseEntity<?> transferMoney(@RequestBody TransferMoneyRequest transferMoneyRequest){

        ProcessInstanceWithVariables transferMoneyProcess = runtimeService.createProcessInstanceByKey("transferMoneyProcess")
                .setVariable("transferMoneyRequest",transferMoneyRequest)
                .executeWithVariablesInReturn();


        CommonResponse response = transferMoneyProcess.getVariables().getValue("response", CommonResponse.class);
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }



}

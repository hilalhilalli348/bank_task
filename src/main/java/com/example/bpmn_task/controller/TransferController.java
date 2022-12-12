package com.example.bpmn_task.controller;

import com.example.bpmn_task.dto.request.TransferRequest;
import com.example.bpmn_task.dto.response.CommonResponse;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("v1/transfers")
public class TransferController {

    private final RuntimeService runtimeService;

    @Autowired
    public TransferController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getTransfer(@PathVariable("id") Long id) {
        ProcessInstanceWithVariables cardProcess = runtimeService.createProcessInstanceByKey("getTransferProcess")
                .setVariable("transferID", id)
                .executeWithVariablesInReturn();

        CommonResponse commonResponse = cardProcess.getVariables().getValue("response", CommonResponse.class);
        return ResponseEntity.status(commonResponse.getHttpStatusCode()).body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllTransfer() {
        ProcessInstanceWithVariables cardProcess = runtimeService.createProcessInstanceByKey("getAllTransferProcess")
                .executeWithVariablesInReturn();

        CommonResponse commonResponse = cardProcess.getVariables().getValue("response", CommonResponse.class);
        return ResponseEntity.status(commonResponse.getHttpStatusCode()).body(commonResponse);
    }


    @PostMapping
    public ResponseEntity<?> addTransfer(@RequestBody TransferRequest transferRequest) {
        ProcessInstanceWithVariables cardProcess = runtimeService.createProcessInstanceByKey("addTransferProcess")
                .setVariable("transferRequest", transferRequest)
                .executeWithVariablesInReturn();

        CommonResponse commonResponse = cardProcess.getVariables().getValue("response", CommonResponse.class);
        return ResponseEntity.status(commonResponse.getHttpStatusCode()).body(commonResponse);
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<?> updateTransfer(@RequestBody TransferRequest transferRequest, @PathVariable("id") Long id) {
        ProcessInstanceWithVariables cardProcess = runtimeService.createProcessInstanceByKey("updateTransferProcess")
                .setVariable("transferID",id)
                .setVariable("transferRequest", transferRequest)
                .executeWithVariablesInReturn();

        CommonResponse commonResponse = cardProcess.getVariables().getValue("response", CommonResponse.class);
        return ResponseEntity.status(commonResponse.getHttpStatusCode()).body(commonResponse);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteTransfer(@PathVariable("id") Long id) {
        ProcessInstanceWithVariables cardProcess = runtimeService.createProcessInstanceByKey("removeTransferProcess")
                .setVariable("transferID", id)
                .executeWithVariablesInReturn();

        CommonResponse commonResponse = cardProcess.getVariables().getValue("response", CommonResponse.class);
        return ResponseEntity.status(commonResponse.getHttpStatusCode()).body(commonResponse);
    }




}

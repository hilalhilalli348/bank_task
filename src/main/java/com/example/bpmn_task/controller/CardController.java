package com.example.bpmn_task.controller;

import com.example.bpmn_task.dto.request.CardRequest;
import com.example.bpmn_task.dto.response.CommonResponse;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/cards")
public class CardController {


    private final RuntimeService runtimeService;

    @Autowired
    public CardController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<?> getCard(@PathVariable("id") Long id) {

        ProcessInstanceWithVariables cardProcess = runtimeService.createProcessInstanceByKey("getCardProcess")
                .setVariable("cardID", id)
                .executeWithVariablesInReturn();

        CommonResponse commonResponse = cardProcess.getVariables().getValue("response", CommonResponse.class);

        return ResponseEntity.status(commonResponse.getHttpStatusCode()).body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllCard() {
        ProcessInstanceWithVariables cardProcess = runtimeService.createProcessInstanceByKey("getAllCardProcess")
                .executeWithVariablesInReturn();

        CommonResponse commonResponse = cardProcess.getVariables().getValue("response", CommonResponse.class);

        return ResponseEntity.status(commonResponse.getHttpStatusCode()).body(commonResponse);
    }

    @PostMapping
    public ResponseEntity<?> addCard(@RequestBody CardRequest cardRequest) {
        ProcessInstanceWithVariables cardProcess = runtimeService.createProcessInstanceByKey("addCardProcess")
                .setVariable("cardRequest", cardRequest)
                .executeWithVariablesInReturn();

        CommonResponse commonResponse = cardProcess.getVariables().getValue("response", CommonResponse.class);

        return ResponseEntity.status(commonResponse.getHttpStatusCode()).body(commonResponse);
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<?> updateCard(@RequestBody CardRequest cardRequest, @PathVariable("id") Long id) {
        ProcessInstanceWithVariables cardProcess = runtimeService.createProcessInstanceByKey("updateCardProcess")
                .setVariable("cardID", id)
                .setVariable("cardRequest", cardRequest)
                .executeWithVariablesInReturn();

        CommonResponse commonResponse = cardProcess.getVariables().getValue("response", CommonResponse.class);

        return ResponseEntity.status(commonResponse.getHttpStatusCode()).body(commonResponse);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteCard(@PathVariable("id") Long id) {
        ProcessInstanceWithVariables cardProcess = runtimeService.createProcessInstanceByKey("removeCardProcess")
                .setVariable("cardID", id)
                .executeWithVariablesInReturn();

        CommonResponse commonResponse = cardProcess.getVariables().getValue("response", CommonResponse.class);

        return ResponseEntity.status(commonResponse.getHttpStatusCode()).body(commonResponse);
    }

}

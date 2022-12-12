package com.example.bpmn_task.delegate.card.addCard;

import com.example.bpmn_task.dto.request.CardRequest;
import com.example.bpmn_task.dto.response.CardResponse;
import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.service.CardService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("addCardDelegate")
public class AddCardDelegate implements JavaDelegate {

    private final CardService cardService;

    @Autowired
    public AddCardDelegate(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        CardRequest cardRequest = (CardRequest) execution.getVariable("cardRequest");
        CardResponse cardResponse =  cardService.addCard(cardRequest);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(Status.of(ErrorEnum.SUCCESS.getStatus(),ErrorEnum.SUCCESS.getMessage()));
        commonResponse.setHttpStatusCode(HttpStatus.OK.value());
        commonResponse.setData(cardResponse);

        execution.setVariable("response",commonResponse);
    }
}

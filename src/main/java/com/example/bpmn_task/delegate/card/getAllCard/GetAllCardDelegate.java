package com.example.bpmn_task.delegate.card.getAllCard;

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

import java.util.List;

@Component("getAllCardDelegate")
public class GetAllCardDelegate implements JavaDelegate {
    private final CardService cardService;

    @Autowired
    public GetAllCardDelegate(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<CardResponse> cardResponseList = cardService.getAllCard();

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(Status.of(ErrorEnum.SUCCESS.getStatus(),ErrorEnum.SUCCESS.getMessage()));
        commonResponse.setHttpStatusCode(HttpStatus.OK.value());
        commonResponse.setData(cardResponseList);

        execution.setVariable("response",commonResponse);
    }
}

package com.example.bpmn_task.delegate.card.getCard;

import com.example.bpmn_task.dto.response.CardResponse;
import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Card;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.mapper.CardMapper;
import com.example.bpmn_task.service.CardService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("getCardDelegate")
public class getCardDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Card card = (Card) execution.getVariable("card");

        CardResponse cardResponse = CardMapper.MAPPER.toCardResponse(card);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(Status.of(ErrorEnum.SUCCESS.getStatus(),ErrorEnum.SUCCESS.getMessage()));
        commonResponse.setHttpStatusCode(HttpStatus.OK.value());
        commonResponse.setData(cardResponse);

        execution.setVariable("response",commonResponse);
    }
}

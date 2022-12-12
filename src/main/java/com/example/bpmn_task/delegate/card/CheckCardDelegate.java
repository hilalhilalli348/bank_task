package com.example.bpmn_task.delegate.card;

import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Card;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.repository.CardRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("checkCardDelegate")
public class CheckCardDelegate implements JavaDelegate {


    private final CardRepository cardRepository;

    @Autowired
    public CheckCardDelegate(CardRepository cardRepository) {

        this.cardRepository = cardRepository;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Long cardID = (Long) execution.getVariable("cardID");
        Card card = cardRepository.findById(cardID).orElse(null);

        execution.setVariable("card", card);
        execution.setVariable("status", Status.of(ErrorEnum.CARD_NOT_FOUND.getStatus(), ErrorEnum.CARD_NOT_FOUND.getMessage()));

    }
}

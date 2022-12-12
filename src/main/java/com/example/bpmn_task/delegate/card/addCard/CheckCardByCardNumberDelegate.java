package com.example.bpmn_task.delegate.card.addCard;

import com.example.bpmn_task.dto.request.CardRequest;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Card;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.repository.CardRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("checkCardByCardNumberDelegate")
public class CheckCardByCardNumberDelegate implements JavaDelegate {
    private final CardRepository cardRepository;

    @Autowired
    public CheckCardByCardNumberDelegate(CardRepository cardRepository) {
        this.cardRepository = cardRepository;

    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        CardRequest cardRequest = (CardRequest) execution.getVariable("cardRequest");
        Long cardNumber = cardRequest.getCardNumber();
        Card card = cardRepository.findByCardNumber(cardNumber).orElse(null);


        execution.setVariable("card", card);
        execution.setVariable("status", Status.of(ErrorEnum.CARD_IS_EXIST.getStatus(), ErrorEnum.CARD_IS_EXIST.getMessage()));
    }
}

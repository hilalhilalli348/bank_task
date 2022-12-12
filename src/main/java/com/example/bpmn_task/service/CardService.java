package com.example.bpmn_task.service;


import com.example.bpmn_task.dto.request.CardRequest;
import com.example.bpmn_task.dto.response.CardResponse;
import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.entity.Card;

import java.util.List;

public interface CardService {
    List<CardResponse> getAllCard();
    CardResponse addCard( CardRequest cardRequest );
    CardResponse updateCard(CardRequest cardRequest,Card card);
    CardResponse deleteCard(Card card);

}

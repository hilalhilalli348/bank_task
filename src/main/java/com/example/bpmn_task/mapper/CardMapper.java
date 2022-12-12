package com.example.bpmn_task.mapper;


import com.example.bpmn_task.dto.request.CardRequest;
import com.example.bpmn_task.dto.response.CardResponse;
import com.example.bpmn_task.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardMapper {
    CardMapper MAPPER = Mappers.getMapper(CardMapper.class);
    @Mapping(source = "account",target = "accountResponse")
    CardResponse toCardResponse(Card card);
    List<CardResponse> toCardResponseList(List<Card> cardList);

    Card toCard(CardRequest cardRequest);
}

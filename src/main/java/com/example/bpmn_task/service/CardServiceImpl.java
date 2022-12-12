package com.example.bpmn_task.service;

import com.example.bpmn_task.dto.request.CardRequest;
import com.example.bpmn_task.dto.response.AccountResponse;
import com.example.bpmn_task.dto.response.CardResponse;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.entity.Card;
import com.example.bpmn_task.mapper.AccountMapper;
import com.example.bpmn_task.mapper.CardMapper;
import com.example.bpmn_task.mapper.CustomerMapper;
import com.example.bpmn_task.repository.AccountRepository;
import com.example.bpmn_task.repository.CardRepository;
import com.example.bpmn_task.service.functional.AccountFunctional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardServiceImpl implements CardService{

    private final CardRepository cardRepository;
    private final AccountFunctional accountFunctional;
    private final AccountRepository accountRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository,  AccountFunctional accountFunctional, AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.accountFunctional = accountFunctional;
        this.accountRepository = accountRepository;
    }



    @Override
    public List<CardResponse> getAllCard() {

        List<Card> cardList = cardRepository.findAll();
        List<CardResponse> cardResponseList = cardList.stream().map(
                card ->
                {
                    CardResponse cardResponse = CardMapper.MAPPER.toCardResponse(card);
                    cardResponse.getAccountResponse().setCustomerResponse(
                            CustomerMapper.MAPPER.toCustomerResponse(card.getAccount().getCustomer())
                    );
                    return cardResponse;
                }

        ).toList();


        return cardResponseList;
    }

    @Transactional
    @Override
    public CardResponse addCard(CardRequest cardRequest) {
        Card card = CardMapper.MAPPER.toCard(cardRequest);
        Account account = accountFunctional.findByID(cardRequest.getAccount().getId());

        card.setBalance(account.getBalance().add(card.getBalance()));

        cardRepository.save(card);

        account.setBalance(account.getBalance().add(cardRequest.getBalance()));
        account.getCardList().forEach(
                card1 -> card1.setBalance(account.getBalance())
        );
        Account savedAccount = accountRepository.save(account);

        AccountResponse accountResponse = AccountMapper.MAPPER.toAccountResponse(savedAccount);
        CardResponse cardResponse = CardMapper.MAPPER.toCardResponse(card);

        cardResponse.setAccountResponse(accountResponse);

        return cardResponse;
    }

    @Override
    public CardResponse updateCard(CardRequest cardRequest,Card card) {

        convertCardRequestToCard(cardRequest,card);
        Card updatedCard = cardRepository.save(card);

        Account account = accountFunctional.findByID(updatedCard.getAccount().getId());
        account.setBalance(updatedCard.getBalance());

        accountFunctional.updateAllCard(account);
        accountRepository.save(account);

        CardResponse cardResponse = CardMapper.MAPPER.toCardResponse(updatedCard);

        cardResponse.getAccountResponse().setCustomerResponse(
                CustomerMapper.MAPPER.toCustomerResponse(updatedCard.getAccount().getCustomer())
        );

        return cardResponse;
    }

    @Override
    public CardResponse deleteCard(Card card) {
           cardRepository.delete(card);
           CardResponse cardResponse = CardMapper.MAPPER.toCardResponse(card);
           return cardResponse;
    }

    private Card convertCardRequestToCard(CardRequest cardRequest, Card card) {
        if (cardRequest==null || card==null){
            return null;
        }

        card.setCardNumber(
                cardRequest.getCardNumber()!=null
                ? cardRequest.getCardNumber() :
                        card.getCardNumber()
        );


        card.setBalance(
                cardRequest.getBalance()!=null
                        ? cardRequest.getBalance() :
                        card.getBalance()
        );


        card.setCurrency(
                cardRequest.getCurrency()!=null
                        ? cardRequest.getCurrency() :
                        card.getCurrency()
        );


        card.setStatus(
                cardRequest.getStatus()!=null
                        ? cardRequest.getStatus() :
                        card.getStatus()
        );


        card.setAccount(
                cardRequest.getAccount()!=null
                        ? cardRequest.getAccount() :
                        card.getAccount()
        );


        card.setCreateDate(
                cardRequest.getCreateDate()!=null
                        ? cardRequest.getCreateDate() :
                        card.getCreateDate()
        );

        card.setExpireDate(
                cardRequest.getExpireDate()!=null
                        ? cardRequest.getExpireDate() :
                        card.getExpireDate()
        );


        card.setActive(
                cardRequest.getActive()!=null
                        ? cardRequest.getActive() :
                        card.getActive()
        );

        return card;

    }


}

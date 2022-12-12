package com.example.bpmn_task.service.functional;

import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.exception.BaseException;
import com.example.bpmn_task.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountFunctional {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountFunctional(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findByID(Long id) {
        return accountRepository
                .findById(id)
                .orElseThrow(() -> BaseException.of(ErrorEnum.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND.value()));
    }


    public void updateAllCard(Account updatedAccount) {
        updatedAccount.getCardList().forEach(
                card -> card.setBalance(updatedAccount.getBalance())
        );
    }


}

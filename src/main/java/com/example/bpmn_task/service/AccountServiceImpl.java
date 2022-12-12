package com.example.bpmn_task.service;


import com.example.bpmn_task.dto.request.AccountRequest;
import com.example.bpmn_task.dto.response.AccountResponse;
import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.CustomerResponse;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Account;
import com.example.bpmn_task.entity.Customer;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.exception.BaseException;
import com.example.bpmn_task.mapper.AccountMapper;
import com.example.bpmn_task.mapper.CustomerMapper;
import com.example.bpmn_task.repository.AccountRepository;
import com.example.bpmn_task.service.functional.AccountFunctional;
import com.example.bpmn_task.service.functional.CustomerFunctional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountFunctional accountFunctional;

    private final CustomerFunctional customerFunctional;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountFunctional accountFunctional, CustomerFunctional customerFunctional) {
        this.accountRepository = accountRepository;
        this.accountFunctional = accountFunctional;
        this.customerFunctional = customerFunctional;
    }

    @Override
    public List<AccountResponse> getAllAccount() {
        List<Account> accountList = accountRepository.findAll();
        return AccountMapper.MAPPER.toAccountResponseList(accountList);
    }

    @Override
    public AccountResponse addAccount(AccountRequest accountRequest) {

        Account account = AccountMapper.MAPPER.toAccount(accountRequest);
        Customer customer = customerFunctional.findByID(account.getCustomer().getId());

        Account savedAccount = accountRepository.save(account);
        savedAccount.setCustomer(customer);

        AccountResponse accountResponse = AccountMapper.MAPPER.toAccountResponse(savedAccount);
        return accountResponse;
    }


    @Override
    @Transactional
    public AccountResponse updateAccount(AccountRequest accountRequest, Account account) {
        convertAccountRequestToAccount(accountRequest, account);
        Account updatedAccount = accountRepository.save(account);
        accountFunctional.updateAllCard(updatedAccount);
        return AccountMapper.MAPPER.toAccountResponse(updatedAccount);
    }

    @Override
    @Transactional
    public AccountResponse deleteAccount(Account account) {
        accountRepository.delete(account);
        return AccountMapper.MAPPER.toAccountResponse(account);
    }

    Account convertAccountRequestToAccount(AccountRequest accountRequest, Account account) {

        if (account == null || accountRequest == null) {
            return null;
        }


        account.setAccountNumber(
                accountRequest.getAccountNumber() != null
                        ? accountRequest.getAccountNumber() :
                        account.getAccountNumber()
        );

        account.setIban(
                accountRequest.getIban() != null
                        ? accountRequest.getIban() :
                        account.getIban());

        account.setBalance(
                accountRequest.getBalance() != null
                        ? accountRequest.getBalance() :
                        account.getBalance()
        );

        account.setCurrency(
                accountRequest.getCurrency() != null
                        ? accountRequest.getCurrency() :
                        account.getCurrency()
        );

        account.setCustomer(
                accountRequest.getCustomer() != null
                        ? accountRequest.getCustomer() :
                        account.getCustomer()
        );


        return account;
    }


}

package com.example.bpmn_task.delegate.transferMoney;

import com.example.bpmn_task.dto.request.TransferMoneyRequest;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.enums.TransferTypeEnum;
import com.example.bpmn_task.exception.BaseException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("checkTransferTypeDelegate")
public class CheckTransferTypeDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TransferMoneyRequest transferMoneyRequest = (TransferMoneyRequest) execution.getVariable("transferMoneyRequest");

        if (Objects.isNull(transferMoneyRequest)){
            throw BaseException.of(ErrorEnum.REQUEST_NOT_FOUNT, HttpStatus.NOT_FOUND.value());
        }

        TransferTypeEnum transferType = transferMoneyRequest.getTransferType();

        switch (transferType){
            case CARD_TO_CARD:
                execution.setVariable("transferType",TransferTypeEnum.CARD_TO_CARD);
                break;
            case ACCOUNT_TO_ACCOUNT:
                execution.setVariable("transferType",TransferTypeEnum.ACCOUNT_TO_ACCOUNT);
                break;
            case ACCOUNT_TO_CARD:
                execution.setVariable("transferType",TransferTypeEnum.ACCOUNT_TO_CARD);
                break;

            case CARD_TO_ACCOUNT:
                execution.setVariable("transferType",TransferTypeEnum.CARD_TO_ACCOUNT);
                break;

        }

    }


}

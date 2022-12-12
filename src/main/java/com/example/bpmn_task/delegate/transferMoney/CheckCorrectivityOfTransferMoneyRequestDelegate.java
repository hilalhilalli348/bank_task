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

@Component("checkCorrectivityOfTransferMoneyRequestDelegate")
public class CheckCorrectivityOfTransferMoneyRequestDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        TransferMoneyRequest transferMoneyRequest = (TransferMoneyRequest) execution.getVariable("transferMoneyRequest");

        TransferTypeEnum transferType = transferMoneyRequest.getTransferType();

        if (Objects.isNull(transferType)) {
            throw BaseException.of(ErrorEnum.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value());
        }

        switch (transferType) {
            case CARD_TO_CARD:
                checkCorrectivityOfRequest(transferMoneyRequest.getCreditorCardNumber(), transferMoneyRequest.getDebitorCardNumber(), transferMoneyRequest);
                break;
            case ACCOUNT_TO_ACCOUNT:
                checkCorrectivityOfRequest(transferMoneyRequest.getCreditorAccountNumber(), transferMoneyRequest.getDebitorAccountNumber(), transferMoneyRequest);
                break;
            case ACCOUNT_TO_CARD:
                checkCorrectivityOfRequest(transferMoneyRequest.getCreditorAccountNumber(), transferMoneyRequest.getDebitorCardNumber(), transferMoneyRequest);
                break;
            case CARD_TO_ACCOUNT:
                checkCorrectivityOfRequest(transferMoneyRequest.getCreditorCardNumber(), transferMoneyRequest.getDebitorAccountNumber(), transferMoneyRequest);
                break;
        }


    }

    private static void checkCorrectivityOfRequest(Object object1, Object object2, TransferMoneyRequest transferMoneyRequest) {
        // creditor ,debitor ve gonderilen meblegin geldiyinden emin olmaq ucun yoxlanis
        if (Objects.isNull(object1) ||
                Objects.isNull(object2)) {
            throw BaseException.of(ErrorEnum.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value());
        } else if (Objects.isNull(transferMoneyRequest.getOutgoingMoney())) {
            throw BaseException.of(ErrorEnum.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value());
        }
    }


}

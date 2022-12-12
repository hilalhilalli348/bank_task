package com.example.bpmn_task.delegate.transfer.updateTransfer;

import com.example.bpmn_task.dto.request.TransferRequest;
import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.dto.response.TransferResponse;
import com.example.bpmn_task.entity.Transfer;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.service.TransferService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("updateTransferDelegate")
public class UpdateTransferDelegate implements JavaDelegate {
    private final TransferService transferService;

    @Autowired
    public UpdateTransferDelegate(TransferService transferService) {
        this.transferService = transferService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TransferRequest transferRequest = (TransferRequest) execution.getVariable("transferRequest");
        Transfer transfer = (Transfer) execution.getVariable("transfer");

        TransferResponse transferResponse = transferService.updateTransfer(transferRequest, transfer);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(Status.of(ErrorEnum.SUCCESS.getStatus(),ErrorEnum.SUCCESS.getMessage()));
        commonResponse.setHttpStatusCode(HttpStatus.OK.value());
        commonResponse.setData(transferResponse);

        execution.setVariable("response",commonResponse);

    }
}

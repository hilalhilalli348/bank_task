package com.example.bpmn_task.delegate.transfer.getAllTransfer;

import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.dto.response.TransferResponse;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.service.TransferService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("getAllTransferDelegate")
public class GetAllTransferDelegate implements JavaDelegate {
    private final TransferService transferService;

    @Autowired
    public GetAllTransferDelegate(TransferService transferService) {
        this.transferService = transferService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

       List<TransferResponse> transferResponses = transferService.getAllTransfer();


        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(Status.of(ErrorEnum.SUCCESS.getStatus(),ErrorEnum.SUCCESS.getMessage()));
        commonResponse.setHttpStatusCode(HttpStatus.OK.value());
        commonResponse.setData(transferResponses);

        execution.setVariable("response",commonResponse);
    }
}

package com.example.bpmn_task.delegate.transfer.getTransfer;

import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.dto.response.TransferResponse;
import com.example.bpmn_task.entity.Transfer;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.mapper.TransferMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("getTransferDelegate")
public class getTransferDelegate implements JavaDelegate {



    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Transfer transfer = (Transfer) execution.getVariable("transfer");

        TransferResponse transferResponse = TransferMapper.MAPPER.toTransferResponse(transfer);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(Status.of(ErrorEnum.SUCCESS.getStatus(),ErrorEnum.SUCCESS.getMessage()));
        commonResponse.setData(transferResponse);
        commonResponse.setHttpStatusCode(HttpStatus.OK.value());

        execution.setVariable("response",commonResponse);
    }
}

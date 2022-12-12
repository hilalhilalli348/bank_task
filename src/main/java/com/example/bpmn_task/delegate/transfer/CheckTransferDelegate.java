package com.example.bpmn_task.delegate.transfer;

import com.example.bpmn_task.dto.response.Status;
import com.example.bpmn_task.entity.Transfer;
import com.example.bpmn_task.enums.ErrorEnum;
import com.example.bpmn_task.repository.TransferRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("checkTransferDelegate")
public class CheckTransferDelegate implements JavaDelegate {
    private final TransferRepository transferRepository;

    @Autowired
    public CheckTransferDelegate(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long transferID = (Long) execution.getVariable("transferID");
        Transfer transfer = transferRepository.findById(transferID).orElse(null);

        if (Objects.isNull(transfer)){
            execution.setVariable("status", Status.of(ErrorEnum.TRANSFER_NOT_FOUND.getStatus(), ErrorEnum.TRANSFER_NOT_FOUND.getMessage()));
        }

        execution.setVariable("transfer",transfer);
    }
}

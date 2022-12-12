package com.example.bpmn_task.service;

import com.example.bpmn_task.dto.request.TransferRequest;
import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.TransferResponse;
import com.example.bpmn_task.entity.Transfer;

import java.util.List;


public interface TransferService {
    List<TransferResponse> getAllTransfer();
    TransferResponse addTransfer(TransferRequest transferRequest);
    TransferResponse updateTransfer(TransferRequest transferRequest,Transfer transfer);

    TransferResponse deleteTransfer(Transfer transfer);


}

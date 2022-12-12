package com.example.bpmn_task.mapper;


import com.example.bpmn_task.dto.request.TransferRequest;
import com.example.bpmn_task.dto.response.TransferResponse;
import com.example.bpmn_task.entity.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransferMapper {
    TransferMapper MAPPER = Mappers.getMapper( TransferMapper.class );

    List<TransferResponse> toTransferResponse(List<Transfer> transferList);

    TransferResponse toTransferResponse(Transfer transfer);
    TransferResponse toTransferResponse(TransferRequest transferRequest);

    Transfer toTransfer(TransferRequest transferRequest);

    Transfer toTransfer(TransferResponse transferResponse);
}

package com.example.bpmn_task.service;

import com.example.bpmn_task.dto.request.TransferRequest;
import com.example.bpmn_task.dto.response.TransferResponse;
import com.example.bpmn_task.entity.Transfer;
import com.example.bpmn_task.mapper.TransferMapper;
import com.example.bpmn_task.repository.TransferRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;


    @Autowired
    public TransferServiceImpl(TransferRepository transferRepository ) {
        this.transferRepository = transferRepository;
    }

    @Override
    public List<TransferResponse> getAllTransfer() {
        List<Transfer> transferList = transferRepository.findAll();
        return TransferMapper.MAPPER.toTransferResponse(transferList);
    }

    @Override
    public TransferResponse addTransfer(TransferRequest transferRequest) {
        Transfer transfer = TransferMapper.MAPPER.toTransfer(transferRequest);
        Transfer savedTransfer = transferRepository.save(transfer);
        return TransferMapper.MAPPER.toTransferResponse(savedTransfer);
    }

    @Override
    public TransferResponse updateTransfer(TransferRequest transferRequest,Transfer transfer) {
        fedTransferRequestToTransfer(transferRequest, transfer);
        Transfer savedTransfer = transferRepository.save(transfer);
        return TransferMapper.MAPPER.toTransferResponse(savedTransfer);
    }

    private Transfer fedTransferRequestToTransfer(TransferRequest transferRequest, Transfer transfer) {
        if (transferRequest == null || transfer == null) {
            return null;
        }

        transfer.setCreditorAccountID(
                transferRequest.getCreditorAccountID() != null
                        ? transferRequest.getCreditorAccountID() :
                        transfer.getDebitorAccountID()
        );

        transfer.setCreditorCardID(
                transferRequest.getCreditorCardID() != null
                        ? transferRequest.getCreditorCardID() :
                        transfer.getDebitorCardID()
        );

        transfer.setCreditorAmount(
                transferRequest.getCreditorAmount() != null
                        ? transferRequest.getCreditorAmount() :
                        transfer.getCreditorAmount()
        );

        transfer.setCreditorCurrency(
                transferRequest.getCreditorCurrency() != null
                        ? transferRequest.getCreditorCurrency() :
                        transfer.getCreditorCurrency()
        );


        transfer.setDebitorAccountID(
                transferRequest.getDebitorAccountID() != null
                        ? transferRequest.getDebitorAccountID() :
                        transfer.getDebitorAccountID()
        );

        transfer.setDebitorCardID(
                transferRequest.getDebitorCardID() != null
                        ? transferRequest.getDebitorCardID() :
                        transfer.getDebitorCardID()
        );


        transfer.setDebitorAmount(
                transferRequest.getDebitorAmount() != null
                        ? transferRequest.getDebitorAmount() :
                        transfer.getDebitorAmount()
        );

        transfer.setDebitorCurrency(
                transferRequest.getDebitorCurrency() != null
                        ? transferRequest.getDebitorCurrency() :
                        transfer.getDebitorCurrency()
        );


        transfer.setTransferType(
                transferRequest.getTransferType() != null
                        ? transferRequest.getTransferType() :
                        transfer.getTransferType()
        );


        transfer.setStatus(
                transferRequest.getStatus() != null
                        ? transferRequest.getStatus() :
                        transfer.getStatus()
        );


        return transfer;

    }



    @Override
    public TransferResponse deleteTransfer(Transfer transfer) {
        transferRepository.delete(transfer);
        return TransferMapper.MAPPER.toTransferResponse(transfer);
    }
}

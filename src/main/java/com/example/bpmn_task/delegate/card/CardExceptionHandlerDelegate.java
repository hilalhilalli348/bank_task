package com.example.bpmn_task.delegate.card;

import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.Status;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("cardExceptionHandlerDelegate")
public class CardExceptionHandlerDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Integer httpStatusCode = Integer.valueOf(execution.getVariable("errorCode").toString());
        Status status = (Status) execution.getVariable("status");



        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(status);
        commonResponse.setData(null);
        commonResponse.setHttpStatusCode(httpStatusCode);

        execution.setVariable("response", commonResponse);

    }
}

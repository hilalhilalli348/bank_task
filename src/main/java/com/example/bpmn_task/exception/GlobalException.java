package com.example.bpmn_task.exception;

import com.example.bpmn_task.dto.response.CommonResponse;
import com.example.bpmn_task.dto.response.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(BaseException.class)
    public ResponseEntity exceptionHandle(BaseException baseException) {

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(
                new Status(
                        baseException.getStatusCode(),
                        baseException.getMessage())
        );
        commonResponse.setHttpStatusCode(baseException.getHttpStatusCode());


        return ResponseEntity
                .status(baseException.getHttpStatusCode())
                .body(commonResponse);
    }


}

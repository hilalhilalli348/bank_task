package com.example.bpmn_task.exception;


import com.example.bpmn_task.enums.ErrorEnum;

public class BaseException extends RuntimeException {

    private Integer statusCode;
    private Integer httpStatusCode;
    private String message;


    public BaseException(Integer statusCode, String message, Integer httpStatusCode) {
        this.statusCode = statusCode;
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    public static BaseException of(ErrorEnum errorEnum, Integer httpStatusCode){
        return new BaseException(
                errorEnum.getStatus(),
                errorEnum.getMessage(),
                httpStatusCode
        );
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

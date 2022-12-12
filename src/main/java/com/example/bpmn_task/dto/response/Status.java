package com.example.bpmn_task.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Status implements Serializable {
    private Integer statusCode;
    private String message;

    @JsonFormat(pattern = "yyyy.MM.dd  hh:mm:ss")
    private LocalDateTime timestamp=LocalDateTime.now();

    public Status() {
    }

    public Status(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public static Status of(Integer statusCode, String message) {
        return new Status(statusCode,message);
    }


    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Status setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}

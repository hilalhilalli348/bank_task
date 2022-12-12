package com.example.bpmn_task.exception;


import java.io.Serializable;
import java.time.LocalDateTime;

public class ErrorDetails<E> implements Serializable {

    private LocalDateTime timestamp = LocalDateTime.now();
    private Integer customStatusCode;
    private String message;

    public ErrorDetails() {
    }

    public ErrorDetails(Integer customStatusCode, String message) {
        this.customStatusCode = customStatusCode;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCustomStatusCode() {
        return customStatusCode;
    }

    public void setCustomStatusCode(Integer customStatusCode) {
        this.customStatusCode = customStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
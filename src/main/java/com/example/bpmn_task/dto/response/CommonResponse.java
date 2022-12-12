package com.example.bpmn_task.dto.response;

import java.io.Serializable;

public class CommonResponse<E>  implements Serializable {

    private Status status;
    private Integer httpStatusCode;
    private E data;

    public CommonResponse() {
    }

    public CommonResponse(Status status, Integer httpStatusCode, E data) {
        this.status = status;
        this.httpStatusCode = httpStatusCode;
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}

package com.example.bpmn_task.enums;

import java.io.Serializable;

public enum ErrorEnum implements Serializable {
    CUSTOMER_NOT_FOUND(101,"Belə Customer tapılmadı !"),
    ACCOUNT_NOT_FOUND(101,"Belə Account tapılmadı !"),
    TRANSFER_NOT_FOUND(101,"Belə Transfer tapılmadı !"),
    CARD_NOT_FOUND(101,"Bele Card tapılmadı !"),
    INVALID_REQUEST(102,"Soğru natamamdır (sorğuda əksiklik var) !"),
    NOT_ENOUGH_BALANCE(103, "Balansda kifayət qədər məbləğ yoxdur !"),
    CARD_DEACTIVATED(104, "Card deaktiv edilib !"),
    ACCOUNT_DEACTIVATED(105,"Account deaktiv edilib !" ),

    CUSTOMER_IS_EXIST(106,"Eyni fin-ə sahib Customer mövcutdur !"),
    ACCOUNT_IS_EXIST(106,"Eyni Account number-lı account mövcutdur !"),
    CARD_IS_EXIST(106,"Eyni Card number-lı card mövcutdur !"),
    REQUEST_NOT_FOUNT(101,"Belə sorğu mövcut deyil !" ),
    SUCCESS(100,"Əməliyyat uğurla tamamlanmışdır" ),
    ACCOUNT_BLOCKED( 107 ,"Account bloklanıb !"),
    CARD_BLOCKED( 107 ,"Card bloklanıb !");


    private int status;
    private String message;

    ErrorEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

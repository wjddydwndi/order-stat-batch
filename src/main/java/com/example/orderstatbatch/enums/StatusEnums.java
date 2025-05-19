package com.example.orderstatbatch.enums;

public enum StatusEnums {

    code_order_status_completed("COMPLETED"),
    code_order_status_canceled("CANCELED");

    String code;

    StatusEnums(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}

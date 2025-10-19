package com.jgfontes.receipt_service.dto;

public enum Type {
    DEBIT(0),
    CREDIT(1);

    private final int code;

    Type(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

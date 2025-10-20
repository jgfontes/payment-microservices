package com.jgfontes.trade_service.model;

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
